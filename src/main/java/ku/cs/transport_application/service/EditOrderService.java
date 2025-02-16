package ku.cs.transport_application.service;

import ku.cs.transport_application.DTO.PaymentResponse;
import ku.cs.transport_application.entity.*;
import ku.cs.transport_application.repository.OrderLineRepository;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.ProductRepository;
import ku.cs.transport_application.repository.ReceiptRepository;
import ku.cs.transport_application.request.OrderRequest;
import ku.cs.transport_application.request.ProductDetailRequest;
import ku.cs.transport_application.service.payment.CalculatePriceService;
import ku.cs.transport_application.service.payment.PaymentFactory;
import ku.cs.transport_application.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EditOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private PaymentFactory paymentFactory;
    @Autowired
    private ReceiptService receiptService;

    @Transactional
    public void editOrder(UUID orderId, OrderRequest request) throws Exception {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found"));
        // ลบ OrderLine เดิม
        List<OrderLine> existingOrderLines = orderLineRepository.findByOrderId(orderId);
        if (!existingOrderLines.isEmpty()) {
            orderLineRepository.deleteByOrderId(orderId);
        }

        // อัปเดตข้อมูลของออเดอร์
        order.setCustomerName(request.getCustomerName());
        order.setCustomerAddress(request.getCustomerAddress());
        order.setTotal(0);

        orderRepository.save(order);

        int totalShippingCost = 0;

        // สร้าง OrderLine และบันทึกทีละตัว
        for (ProductDetailRequest productDetail : request.getProductDetails()) {
            Product product = new Product();
            product.setName(productDetail.getProductName());
            product.setType(productDetail.getProductType());
            productRepository.save(product);

            OrderLine orderLine = new OrderLine();
            orderLine.setOrder(order);
            orderLine.setProduct(product);
            orderLine.setQuantity(productDetail.getQuantity());

            OrderLineKey orderLineKey = new OrderLineKey();
            orderLineKey.setOrderId(order.getId());
            orderLineKey.setProductId(product.getId());
            orderLine.setId(orderLineKey);

            CalculatePriceService priceService = new CalculatePriceService();
            int shippingCost = priceService.calculateShipping(product.getType(), productDetail.getQuantity());
            totalShippingCost += shippingCost;

            // บันทึก OrderLine
            orderLineRepository.save(orderLine);
        }

        // คำนวณราคาทั้งหมด (รวมราคาสินค้าและค่าขนส่ง)
        order.setTotal(totalShippingCost);

        // สร้างลิงก์การชำระเงิน
        PaymentService paymentService = paymentFactory.getPaymentService(request.getPaymentMethod());
        PaymentResponse response = paymentService.createPaymentLink(order);
        order.setPaymentLink(response.getPaymentLink());

        // อัพเดตข้อมูล order ที่มี payment link
        orderRepository.save(order);

        //ลบใบเสร็จอันเก่า
        receiptRepository.deleteByOrderId(orderId);
        //สร้างใบเสร็จใหม่
        receiptService.createReceipt(orderId);
    }
}
