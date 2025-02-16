package microservices.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import microservices.core_service.dto.ProductDto;

@FeignClient(name = "catalog-service", url = "http://localhost:8083")
public interface CatalogClient {
    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    @PutMapping("/products/{id}/stock")
    void decreaseStock(@PathVariable("id") Long id);
}
