package microservices.catalog_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import microservices.catalog_service.service.ProductService;
import microservices.core_service.dto.ProductDto;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProducts() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(new ProductDto(1L, "Product 1", 1L));
        productDtos.add(new ProductDto(2L, "Product 2", 1L));

        when(productService.getProducts())
                .thenReturn(productDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    void testAddProduct() throws Exception {
        ProductDto inputProductDto = new ProductDto(null, "New Product", 1L);
        ProductDto outputProductDto = new ProductDto(1L, "New Product", 1L);
        when(productService.addProduct(ArgumentMatchers.any(ProductDto.class)))
                .thenReturn(outputProductDto);
        String json = objectMapper.writeValueAsString(inputProductDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Product"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        Long productId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"));

        verify(productService).deleteProduct(productId);
    }
}