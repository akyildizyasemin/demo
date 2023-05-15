package com.example.ECommerceApp.business.requests;

import com.example.ECommerceApp.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderProductRequest> orderProducts;
    private Date orderDate;
    private OrderStatus orderStatus;
}
