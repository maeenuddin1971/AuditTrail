package AuditTrail.AuditTrail.log.service;

import AuditTrail.AuditTrail.log.entity.AuditLog;
import AuditTrail.AuditTrail.log.repository.AuditLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepo auditLogRepo;

    public AuditLog saveAuditLog(AuditLog auditLog) {
        AuditLog auditLog1 = this.auditLogRepo.save(auditLog);
        return auditLog1;
    }

    public List<AuditLog> getAuditLogByOrderId(Long orderId) {
        return auditLogRepo.findAuditLogByOrderId(orderId);
    }
}
