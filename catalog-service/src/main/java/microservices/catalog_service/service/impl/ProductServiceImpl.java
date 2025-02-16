package microservices.catalog_service.service.impl;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.catalog_service.constants.ErrorMessages;
import microservices.catalog_service.entity.Product;
import microservices.catalog_service.entity.mapper.ProductMapper;
import microservices.catalog_service.exception.EmptyException;
import microservices.catalog_service.repository.ProductRepository;
import microservices.catalog_service.service.ProductService;
import microservices.core_service.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(ProductMapper::mapToProductDto).toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EmptyException(ErrorMessages.PRODUCT_NOT_EXIST));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product addedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(addedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public void decreaseProductStock(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EmptyException(ErrorMessages.PRODUCT_NOT_EXIST));
        if (product.getStockCount() >= 1)
            product.setStockCount(product.getStockCount() - 1);
        productRepository.save(product);
    }

}
