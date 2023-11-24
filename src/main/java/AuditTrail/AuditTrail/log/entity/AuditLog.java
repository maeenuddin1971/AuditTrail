package AuditTrail.AuditTrail.log.entity;

import AuditTrail.AuditTrail.log.allenum.EventType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sys_audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime eventDateAndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String accessedUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order orderId;
}
