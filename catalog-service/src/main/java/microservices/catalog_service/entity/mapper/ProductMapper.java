package microservices.catalog_service.entity.mapper;

import microservices.catalog_service.entity.Product;
import microservices.core_service.dto.ProductDto;

public class ProductMapper {
    private ProductMapper() {

    }

    public static ProductDto mapToProductDto(Product product) {
        if (product == null) {
            return null; // Handle null input
        }
        return new ProductDto(product.getId(), product.getName(), product.getStockCount());
    }

    public static Product mapToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null; // Handle null input
        }
        return new Product(productDto.id(), productDto.name(), productDto.stockCount());
    }
}
