package com.example.ECommerceApp.webApi.controllers;

import com.example.ECommerceApp.business.abstracts.OrderService;
import com.example.ECommerceApp.business.requests.CreateOrderRequest;
import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.enums.OrderStatus;
import com.example.ECommerceApp.exception.InvalidStateException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int orderId) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(orderId);

            OrderResponse response = orderService.getOrderById(orderResponse);
            return ResponseEntity.ok(response);
        }


    @GetMapping("/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderResponse> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@PathVariable int customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        List<OrderResponse> orders = orderService.getOrdersByCustomerId(customerResponse);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderStatus> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderStatus.CREATED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderStatus> cancelOrder(@PathVariable int orderId) {
        OrderResponse orderResponse = orderService.getOrderById(new OrderResponse(orderId));
        orderService.cancelOrder(orderResponse);
        return ResponseEntity.status(HttpStatus.OK).body(OrderStatus.CANCELLED);
    }

    @PutMapping("/users/{userId}/address")
    public ResponseEntity<Void> updateOrderAddressByUserId(@PathVariable int userId, @RequestBody String newAddress) {
        orderService.updateOrderAddressByUserId(userId, newAddress);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/markShipped")
    public ResponseEntity<Void> markOrderAsShipped(@PathVariable int orderId) {
        OrderResponse orderResponse = orderService.getOrderById(new OrderResponse(orderId));

        if (orderResponse.getOrderStatus() != OrderStatus.CREATED) {
            throw new InvalidStateException("Cannot mark order as shipped. Invalid order status.");
        }

        orderService.markOrderAsShipped(orderResponse);
        return ResponseEntity.noContent().build();
    }


}
