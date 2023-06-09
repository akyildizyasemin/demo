package com.example.ECommerceApp.business.responses;

import com.example.ECommerceApp.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private int orderId;
    private int customerId;
    private List<OrderProductResponse> orderProducts;
    private Date orderDate;
    private OrderStatus orderStatus;

    public OrderResponse(int orderId) {
        this.orderId = orderId;
    }
}
