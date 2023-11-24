package AuditTrail.AuditTrail.log.entity;

import AuditTrail.AuditTrail.log.allenum.NotificationPriority;
import AuditTrail.AuditTrail.log.allenum.NotificationType;
import lombok.Data;
import java.time.LocalDateTime;


import javax.persistence.*;

@Entity
@Data
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime creationDateAndTime;
    private LocalDateTime expirationDate;

    private Boolean isSeen = Boolean.FALSE;
    private LocalDateTime seenTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private User notificationTo;

    @Enumerated(EnumType.STRING)
    private NotificationPriority priority;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
