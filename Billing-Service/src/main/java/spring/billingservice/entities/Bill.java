package spring.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import spring.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long cutsomerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductIteam> productIteams = new ArrayList<>();
    @Transient private Customer customer;
}
