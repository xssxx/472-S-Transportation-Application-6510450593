package ku.cs.transport_application.repository;

import ku.cs.transport_application.common.ProductType;
import ku.cs.transport_application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
