package ku.cs.transport_application.entity;

import jakarta.persistence.*;
import ku.cs.transport_application.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    private OrderStatus status;
    private String customerName;
    private String customerAddress;
    private LocalDateTime date;
    private LocalDateTime deliveredDate;
    private String shipmentDocDir;
    private double total;

    @Column(columnDefinition = "TEXT")
    private String paymentLink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private TransportationWorker worker;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();
}
