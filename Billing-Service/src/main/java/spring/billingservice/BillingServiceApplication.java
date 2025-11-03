package spring.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import spring.billingservice.entities.Bill;
import spring.billingservice.entities.ProductIteam;
import spring.billingservice.feign.CustomerRestClient;
import spring.billingservice.feign.ProductRestClient;
import spring.billingservice.model.Customer;
import spring.billingservice.model.Product;
import spring.billingservice.repository.BillRepository;
import spring.billingservice.repository.ProductIteamRepository;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductIteamRepository productIteamRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient) {
        return args -> {
            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();

            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .cutsomerId(customer.getId())
                        .build();
                billRepository.save(bill);


                products.forEach(product -> {
                    ProductIteam productIteam = ProductIteam.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1 + new Random().nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productIteamRepository.save(productIteam);
                });
            });
        };
    }
}
