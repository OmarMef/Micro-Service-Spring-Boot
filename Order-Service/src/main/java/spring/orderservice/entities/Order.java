package spring.orderservice.entities;


import jakarta.persistence.*;
import lombok.*;
import spring.orderservice.enums.OrderStatus;
import spring.orderservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long customerId;
    @OneToMany(mappedBy = "order")
    private List<ProductIteam> productIteams = new ArrayList<>();
    @Transient private Customer customer;
}
