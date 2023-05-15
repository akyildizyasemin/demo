package com.example.ECommerceApp.business.abstracts;

import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.entities.concretes.OrderProduct;

import java.util.List;

public interface OrderProductService {

    public List<OrderProduct> getOrderProducts(OrderResponse orderResponse);


}
