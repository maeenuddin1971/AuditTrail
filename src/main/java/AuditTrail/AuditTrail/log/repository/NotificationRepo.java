package AuditTrail.AuditTrail.log.repository;

import AuditTrail.AuditTrail.log.entity.Notification;
import AuditTrail.AuditTrail.log.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findAllByNotificationToAndIsSeen(User user, Boolean isSeen);

    @Query("select n from Notification n where n.id = ?1 and n.notificationTo.id = ?2")
    Notification getNotificationByIdAndUser(Long id, Long userId);
}
