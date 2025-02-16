package microservices.order_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import feign.FeignException;
import microservices.order_service.exception.ProductNotFoundException;
import microservices.order_service.feign.CatalogClient;
import microservices.order_service.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CatalogClient catalogClient;

    @Override
    public void orderProductById(Long productId) {
        try {
            catalogClient.getProductById(productId);
            catalogClient.decreaseStock(productId);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ProductNotFoundException("PRODUCT_NOT_FOUND");
            } else {
                throw ex;
            }
        }
    }

}
