package AuditTrail.AuditTrail.log.controller;

import AuditTrail.AuditTrail.log.dto.NotificationDTO;
import AuditTrail.AuditTrail.log.dto.response.MessageResponse;
import AuditTrail.AuditTrail.log.entity.Notification;
import AuditTrail.AuditTrail.log.entity.User;
import AuditTrail.AuditTrail.log.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getAllUnseenNotificationForUser/{userId}")
    public ResponseEntity<?> getAllUnseenNotificationForUser(@PathVariable(name = "userId") Long id) {
        List<Notification> notificationList = notificationService.getAllUnseenNotificationForUser(id);
        List<NotificationDTO> notificationDTOList = convertNotificationToNotificationDTO(notificationList);
        // Have to use DTO
        return new ResponseEntity<>(new MessageResponse("Got data", true, notificationDTOList), HttpStatus.OK);
    }

    @PutMapping("/clickedOnNotification/{notificationId}")
    public ResponseEntity<?> clickedOnNotification(@PathVariable(name = "notificationId") Long notificationId) {
        // At first will get the logged in user and will match with the NotificationId
        User user = new User();
        Notification notification = notificationService.getNotificationByIdAndUser(notificationId, user.getId());
        if (notification != null) {
            notification.setIsSeen(Boolean.TRUE);
            notification.setSeenTime(LocalDateTime.now());
            notificationService.saveNotification(notification);
            return new ResponseEntity<>(new MessageResponse("Data is updated", true), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageResponse("No data is found", false), HttpStatus.OK);
    }

    public List<NotificationDTO> convertNotificationToNotificationDTO(List<Notification> notificationList) {
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for(Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setId(notification.getId());
            notificationDTO.setMessage(notification.getMessage());
            notificationDTO.setCreationDateAndTime(notification.getCreationDateAndTime());
            notificationDTO.setExpirationDate(notification.getExpirationDate());
            notificationDTO.setIsSeen(notification.getIsSeen());
            notificationDTO.setSeenTime(notification.getSeenTime());
            notificationDTO.setUserId(notification.getNotificationTo().getId());
            notificationDTO.setUserName(notification.getNotificationTo().getName());
            notificationDTO.setPriority(notification.getPriority());
            notificationDTO.setNotificationType(notification.getNotificationType());
            notificationDTOList.add(notificationDTO);
        }
        return notificationDTOList;
    }

}
