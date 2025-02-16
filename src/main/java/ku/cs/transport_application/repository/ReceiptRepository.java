package ku.cs.transport_application.repository;

import ku.cs.transport_application.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.id = :orderId")
    void deleteByOrderId(UUID orderId);
}
