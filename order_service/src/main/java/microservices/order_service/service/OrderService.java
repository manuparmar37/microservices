package microservices.order_service.service;

import java.util.List;

import microservices.core_service.dto.ProductDto;

public interface OrderService {
    public void orderProductById(Long productId);
}
