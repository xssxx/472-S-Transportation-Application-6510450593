package ku.cs.transport_application.entity;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {
    @Id @GeneratedValue                             // Auto Generated UUID
    @JdbcTypeCode(SqlTypes.VARCHAR)                 // Cast Type to VARCHAR(36)
    @Column(name = "history_id", nullable = false)  // Set name, Not null
    private UUID id;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne @JsonIgnore @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
