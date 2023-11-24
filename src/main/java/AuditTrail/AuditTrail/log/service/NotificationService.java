package AuditTrail.AuditTrail.log.service;

import AuditTrail.AuditTrail.log.entity.Notification;
import AuditTrail.AuditTrail.log.entity.User;
import AuditTrail.AuditTrail.log.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    public List<Notification> getAllUnseenNotificationForUser(Long userId) {
        User user = new User();
        user.setId(userId);
        Boolean isSeen = Boolean.FALSE;

        List<Notification> notificationList = notificationRepo.findAllByNotificationToAndIsSeen(user, isSeen);
        return notificationList;
    }

    public Notification getNotificationById(Long id) {
        Notification notification = notificationRepo.findById(id).orElse(null);
        return notification;
    }

    public Notification getNotificationByIdAndUser(Long id , Long userId) {
        Notification notification = notificationRepo.getNotificationByIdAndUser(id, userId);
        return notification;
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

}
