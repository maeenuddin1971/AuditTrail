package AuditTrail.AuditTrail.log.repository;

import AuditTrail.AuditTrail.log.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
