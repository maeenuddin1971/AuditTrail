package AuditTrail.AuditTrail.log.repository;

import AuditTrail.AuditTrail.log.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
