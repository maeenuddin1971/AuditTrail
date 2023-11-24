package AuditTrail.AuditTrail.log.dto;

import AuditTrail.AuditTrail.log.allenum.EventType;
import AuditTrail.AuditTrail.log.entity.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditLogDTO {
    private Long id;
    private EventType eventType;
    private LocalDateTime eventDateAndTime;
    private String userName;
    private String accessedUrl;
    private Order orderId;

    private String userMail;
}
