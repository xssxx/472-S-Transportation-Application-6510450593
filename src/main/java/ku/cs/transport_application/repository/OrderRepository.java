package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByUserId(UUID userId);
    List<Order> findByWorkerId(UUID workerId);
//    @Query("SELECT o FROM Order o WHERE "+
//            "LOWER(o.customerName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(o.customerAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(o.worker.id) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(o.user.id) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(o.id) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//            "LOWER(o.status.name()) LIKE LOWER(CONCAT('%', :keyword, '%'))"
//    )
//    List<Order> searchOrder(String keyword);
List<Order> findByCustomerName(String customerName);
}
