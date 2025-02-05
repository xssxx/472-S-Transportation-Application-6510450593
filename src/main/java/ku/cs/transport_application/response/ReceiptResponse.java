package ku.cs.transport_application.response;

import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.common.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ReceiptResponse {
    private UUID o_id;

    private LocalDateTime date;
    private OrderStatus status;
    private String cus_name;
    private String cus_address;
    private String worker;

    private UUID p_id;
    private String p_name;
    private ProductType p_type;
    private double p_amount;
}
