package ku.cs.transport_application.service;

import ku.cs.transport_application.DTO.OrderDTO;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.OrderLine;
import ku.cs.transport_application.entity.Receipt;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.ReceiptRepository;
import ku.cs.transport_application.response.ReceiptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceiptService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private OrderService orderService;

    //สร้างใบเสร็จ
    public void createReceipt(UUID orderId) throws Exception {
        Receipt receipt = new Receipt();
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            receipt.setOrder(order);
            receipt.setCreateAt(LocalDateTime.now());
            receiptRepository.save(receipt);
        } else {
            // Handle case where the order is not found, possibly throwing an exception
            throw new Exception("Order not found.");
        }
    }


    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(UUID id) {
        return receiptRepository.findById(id);
    }

//    public Optional<ReceiptResponse> getReceiptByOrderId(UUID o_id){
//        Order order = orderService.getOrdersByOrderId(o_id);
//        ReceiptResponse receipt = new ReceiptResponse(
//                order.getId(),
//                order.getDate(),
//                order.getStatus(),
//                order.getCustomerName(),
//                order.getCustomerAddress(),
//                order.getWorker().getName()
//                order.getOrderLines().getProductId(),
//                order.getOrderLines().getProductName(),
//                order.getOrderLines().getProductAmount(),
//                order.getOrderLines().getProductType()
//        )
//        );
//    }
}
