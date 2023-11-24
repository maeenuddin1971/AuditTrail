package AuditTrail.AuditTrail.log.controller;

import AuditTrail.AuditTrail.log.allenum.EventType;
import AuditTrail.AuditTrail.log.allenum.NotificationPriority;
import AuditTrail.AuditTrail.log.allenum.NotificationType;
import AuditTrail.AuditTrail.log.entity.AuditLog;
import AuditTrail.AuditTrail.log.entity.Notification;
import AuditTrail.AuditTrail.log.entity.Order;
import AuditTrail.AuditTrail.log.entity.User;
import AuditTrail.AuditTrail.log.service.AuditService;
import AuditTrail.AuditTrail.log.service.NotificationService;
import AuditTrail.AuditTrail.log.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController("/order")
public class OrderController {


    @Autowired
    NotificationService notificationService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuditService auditService;

    @Transactional
    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {

        // later have to setup with the logged In user
        order.setUser(new User());
        // later have to calculate price based on the prices of the order
        order.setPrice(BigDecimal.valueOf(0));

        Order order2 = orderService.saveOrder(order);

        // now save in the audit
        AuditLog auditLog = new AuditLog();
        auditLog.setUser(order.getUser());
        auditLog.setOrderId(order2);
        auditLog.setEventDateAndTime(LocalDateTime.now());
        auditLog.setEventType(EventType.ORDER);
        String currentUrl = request.getRequestURL().toString();
        auditLog.setAccessedUrl(currentUrl);
        auditService.saveAuditLog(auditLog);

        // Now Give an initial notification to the user that his order has been placed
        Notification notification = new Notification();
        notification.setMessage("Your Order at " + order2.getOrderDate() + " Has been Placed");
        notification.setNotificationType(NotificationType.ORDER);
        LocalDateTime todayDateAndTime = LocalDateTime.now();
        notification.setCreationDateAndTime(todayDateAndTime);
        notification.setExpirationDate(todayDateAndTime.plusDays(7));
        notification.setPriority(NotificationPriority.MEDIUM);
        notification.setNotificationTo(order2.getUser());
        notificationService.saveNotification(notification);

        return new ResponseEntity<>("Data is saved", HttpStatus.OK);
    }

}
