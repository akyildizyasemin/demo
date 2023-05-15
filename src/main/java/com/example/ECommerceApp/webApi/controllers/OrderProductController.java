package com.example.ECommerceApp.webApi.controllers;

import com.example.ECommerceApp.business.abstracts.OrderProductService;
import com.example.ECommerceApp.business.abstracts.OrderService;
import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.entities.concretes.OrderProduct;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/order-products")
public class OrderProductController {

    private OrderService orderService;
    private OrderProductService orderProductService;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderProduct>> getOrderProductsByOrderId(@PathVariable int orderId) {
        OrderResponse orderResponse = orderService.getOrderById(new OrderResponse(orderId));
        List<OrderProduct> orderProducts = orderProductService.getOrderProducts(orderResponse);
        return ResponseEntity.ok(orderProducts);
    }

}
