package ku.cs.transport_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import ku.cs.transport_application.common.TransportationWorkerStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class TransportationWorker {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private TransportationWorkerStatus status;

    @OneToMany(mappedBy = "worker")
    private List<Order> orders = new ArrayList<>();

}
