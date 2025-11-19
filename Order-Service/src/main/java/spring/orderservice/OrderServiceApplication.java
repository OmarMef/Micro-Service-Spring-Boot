package spring.orderservice;

import org.hibernate.engine.spi.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import spring.orderservice.entities.Order;
import spring.orderservice.entities.ProductIteam;
import spring.orderservice.enums.OrderStatus;
import spring.orderservice.feign.CustomerRestClient;
import spring.orderservice.feign.ProductRestClient;
import spring.orderservice.model.Customer;
import spring.orderservice.model.Product;
import spring.orderservice.repository.OrderRepository;
import spring.orderservice.repository.ProductIteamRepository;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository,
                                        ProductIteamRepository productIteamRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient) {
        return args -> {
            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();
            Random random = new Random();

            for (int i=0; i<10; i++) {
                customers.forEach(customer -> {
                    Order order = Order.builder()
                            .createdAt(new Date())
                            .status(OrderStatus.values()[random.nextInt(OrderStatus.values().length)])
                            .customerId(customer.getId())
                            .build();
                    orderRepository.save(order);


                    products.forEach(product -> {
                        ProductIteam productIteam = ProductIteam.builder()
                                .order(order)
                                .productId(product.getId())
                                .quantity(new Random().nextInt(product.getQuantity()))
                                .discount(random.nextDouble(1))
                                .unitPrice(product.getPrice())
                                .build();
                        productIteamRepository.save(productIteam);
                    });
                });
            }
        };
    }
}
