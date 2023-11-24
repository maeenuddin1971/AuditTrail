package AuditTrail.AuditTrail.log.service;

import AuditTrail.AuditTrail.log.entity.Order;
import AuditTrail.AuditTrail.log.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Order saveOrder(Order order) {
        Order order1 = orderRepo.save(order);
        return order1;
    }

}
