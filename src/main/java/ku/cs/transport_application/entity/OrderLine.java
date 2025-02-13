package ku.cs.transport_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderLine {
    @EmbeddedId
    private OrderLineKey id;

    private int quantity;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

}
