package com.example.ECommerceApp.business.abstracts;

import com.example.ECommerceApp.business.requests.CreateCategoryRequest;
import com.example.ECommerceApp.business.requests.CreateOrderRequest;
import com.example.ECommerceApp.business.requests.UpdateCategoryRequest;
import com.example.ECommerceApp.business.responses.CategoryResponse;
import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    void createOrder(CreateOrderRequest createOrderRequest);
    void cancelOrder(OrderResponse orderResponse);
    void updateOrderAddressByUserId(int userId, String newAddress);

    void markOrderAsShipped(OrderResponse orderResponse);
    void markOrderAsDelivered(OrderResponse orderResponse);

    OrderResponse getOrderById(OrderResponse orderResponse);
    List<OrderResponse> getOrdersByStatus(OrderStatus orderStatus);
    List<OrderResponse> getOrdersByCustomerId(CustomerResponse customerResponse);


}
