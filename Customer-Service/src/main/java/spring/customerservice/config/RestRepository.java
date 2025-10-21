package spring.customerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import spring.customerservice.entities.Customer;

@Configuration
public class RestRepository implements RepositoryRestConfigurer {
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config , CorsRegistry cors) {
        config.exposeIdsFor(Customer.class);
    }
}
