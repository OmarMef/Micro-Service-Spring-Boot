package spring.inventoryservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import spring.inventoryservice.entities.Product;

@Configuration
public class RestRepository implements RepositoryRestConfigurer {
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config , CorsRegistry cors) {
        config.exposeIdsFor(Product.class);
    }
}
