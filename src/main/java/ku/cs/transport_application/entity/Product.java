package ku.cs.transport_application.entity;

import jakarta.persistence.*;
import ku.cs.transport_application.common.ProductType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private ProductType type;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();
}
