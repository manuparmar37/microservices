package microservices.catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import microservices.catalog_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
