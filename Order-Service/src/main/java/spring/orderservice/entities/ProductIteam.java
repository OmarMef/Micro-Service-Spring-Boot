package spring.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import spring.orderservice.model.Product;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter
@Setter
public class ProductIteam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private double discount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
    private int quantity;
    private double unitPrice;
    @Transient private Product product;

    public double getAmount() {
        return unitPrice*quantity*(1-discount);
    }
}
