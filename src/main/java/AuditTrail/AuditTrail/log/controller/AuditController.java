package AuditTrail.AuditTrail.log.controller;

import AuditTrail.AuditTrail.log.dto.AuditLogDTO;
import AuditTrail.AuditTrail.log.entity.AuditLog;
import AuditTrail.AuditTrail.log.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/audit")
public class AuditController {


    @Autowired
    private AuditService auditService;

    @GetMapping("/getAuditTrail/{orderId}")
    public ResponseEntity<?> getAuditTrail(@PathVariable(name = "orderId") Long orderId) {
        List<AuditLog> auditLogList = this.auditService.getAuditLogByOrderId(orderId);
        List<AuditLogDTO> auditLogDTOList = convertAuditLogToAuditLogDTO(auditLogList);
        return new ResponseEntity<>(auditLogDTOList, HttpStatus.OK);
    }

    public List<AuditLogDTO> convertAuditLogToAuditLogDTO(List<AuditLog> auditLogList) {
        List<AuditLogDTO> auditLogDTOList = new ArrayList<>();

        for (AuditLog auditLog : auditLogList) {
            AuditLogDTO auditLogDTO = new AuditLogDTO();
            auditLogDTO.setId(auditLog.getId());
            auditLogDTO.setOrderId(auditLog.getOrderId());
            auditLogDTO.setUserMail(auditLog.getUser().getEmail());
            auditLogDTO.setUserName(auditLog.getUser().getName());
            auditLogDTO.setAccessedUrl(auditLog.getAccessedUrl());
            auditLogDTO.setEventType(auditLog.getEventType());
            auditLogDTO.setEventDateAndTime(auditLog.getEventDateAndTime());
            auditLogDTOList.add(auditLogDTO);
        }
        return auditLogDTOList;
    }

}
