package ku.cs.transport_application.repository;

import ku.cs.transport_application.entity.Receipt;
import ku.cs.transport_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    Optional<Receipt> findById(UUID id);
}
