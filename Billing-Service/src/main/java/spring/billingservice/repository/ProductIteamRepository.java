package spring.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.billingservice.entities.ProductIteam;

public interface ProductIteamRepository extends JpaRepository<ProductIteam, Long> {
}
