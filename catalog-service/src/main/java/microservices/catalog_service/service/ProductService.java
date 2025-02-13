package microservices.catalog_service.service;

import java.util.List;

import microservices.core_service.dto.ProductDto;

public interface ProductService {
    public List<ProductDto> getProducts();

    public ProductDto addProduct(ProductDto productDto);

    public void deleteProduct(Long productId);
}
