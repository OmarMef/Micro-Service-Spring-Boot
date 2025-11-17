package spring.orderservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.orderservice.entities.Order;
import spring.orderservice.feign.CustomerRestClient;
import spring.orderservice.feign.ProductRestClient;
import spring.orderservice.repository.OrderRepository;
import spring.orderservice.repository.ProductIteamRepository;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductIteamRepository productIteamRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;



    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id).get();
        order.setCustomer(customerRestClient.findCustomerById(order.getCustomerId()));
        order.getProductIteams().forEach(productIteam -> {
            productIteam.setProduct(productRestClient.findProductById(productIteam.getProductId()));
        });
        return order;
    }
}
