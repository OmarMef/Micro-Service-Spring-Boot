package spring.billingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.billingservice.entities.Bill;
import spring.billingservice.feign.CustomerRestClient;
import spring.billingservice.feign.ProductRestClient;
import spring.billingservice.repository.BillRepository;
import spring.billingservice.repository.ProductIteamRepository;

@RestController
public class BillController {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductIteamRepository productIteamRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;


    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductIteams().forEach(productIteam -> {
            productIteam.setProduct(productRestClient.findProductById(productIteam.getProductId()));
        });
        return bill;
    }
}
