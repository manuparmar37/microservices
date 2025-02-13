package microservices.catalog_service.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservices.catalog_service.entity.Product;
import microservices.catalog_service.repository.ProductRepository;
import microservices.core_service.dto.ProductDto;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testAddProduct() {
        ProductDto productDto = new ProductDto(null, "Ketchup", 1L);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Ketchup");
        savedProduct.setStockCount(1L);

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(savedProduct);

        ProductDto addedProductDto = productService.addProduct(productDto);

        assertNotNull(addedProductDto);
        assertEquals(1L, addedProductDto.id());
        assertEquals("Ketchup", addedProductDto.name());
    }

    @Test
    void testGetProducts() {
        when(productRepository.findAll())
                .thenReturn(List.of(new Product(1L, "Product 1", 1L), new Product(2L, "Product 2", 1L)));

        List<ProductDto> products = productService.getProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).id());
        assertEquals("Product 1", products.get(0).name());
        assertEquals(2L, products.get(1).id());
        assertEquals("Product 2", products.get(1).name());
    }

    @Test
    void deleteProductShouldDeleteProductSuccessfully() {
        Long id = 1L;
        doNothing().when(productRepository).deleteById(id);
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(id);

    }

}
