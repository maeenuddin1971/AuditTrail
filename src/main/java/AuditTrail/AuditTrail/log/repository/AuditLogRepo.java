package AuditTrail.AuditTrail.log.repository;

import AuditTrail.AuditTrail.log.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepo extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findAuditLogByOrderId(Long orderId);
}
