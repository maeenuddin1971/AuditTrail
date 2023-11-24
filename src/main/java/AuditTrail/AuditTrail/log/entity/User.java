package AuditTrail.AuditTrail.log.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;
}
