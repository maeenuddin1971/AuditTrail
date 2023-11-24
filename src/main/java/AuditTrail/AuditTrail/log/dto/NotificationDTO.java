package AuditTrail.AuditTrail.log.dto;

import AuditTrail.AuditTrail.log.allenum.NotificationPriority;
import AuditTrail.AuditTrail.log.allenum.NotificationType;
import AuditTrail.AuditTrail.log.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationDTO {

    private Long id;

    private String message;
    private LocalDateTime creationDateAndTime;
    private LocalDateTime expirationDate;

    private Boolean isSeen;
    private LocalDateTime seenTime;

    private String userName;
    private Long userId;
    private NotificationPriority priority;
    private NotificationType notificationType;

}
