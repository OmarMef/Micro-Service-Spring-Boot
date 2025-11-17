package spring.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.orderservice.entities.ProductIteam;

public interface ProductIteamRepository extends JpaRepository<ProductIteam, Long> {
}
