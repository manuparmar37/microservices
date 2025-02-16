package microservices.order_service.controller;

import microservices.order_service.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order/{productId}")
    public ResponseEntity<String> orderProduct(@PathVariable("productId") Long productId) {
        orderService.orderProductById(productId);
        return ResponseEntity.ok("Ordered Successfully");
    }

}
