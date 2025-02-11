package ku.cs.transport_application.request;

import ku.cs.transport_application.entity.History;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateHistoryRequest {
    private UUID userId;
    private int amount;
    private String method;
}
