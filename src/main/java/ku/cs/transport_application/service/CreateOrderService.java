package ku.cs.transport_application.service;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.*;
import ku.cs.transport_application.repository.OrderLineRepository;
import ku.cs.transport_application.repository.OrderRepository;
import ku.cs.transport_application.repository.ProductRepository;
import ku.cs.transport_application.repository.UserRepository;
import ku.cs.transport_application.request.OrderRequest;
import ku.cs.transport_application.request.ProductDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerAddress(request.getCustomerAddress());
        order.setStatus(OrderStatus.UNCHECK);
        order.setDate(LocalDateTime.now());

        order.setUser(userRepository.findByUsername(request.getUsername()));

        orderRepository.save(order);

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

            orderLineRepository.save(orderLine);
        }

    }
}