package spring.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
    private long id;
    private String name;
    private String email;
}
