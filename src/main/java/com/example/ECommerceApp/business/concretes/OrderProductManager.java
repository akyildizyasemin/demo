package com.example.ECommerceApp.business.concretes;

import com.example.ECommerceApp.business.abstracts.OrderProductService;
import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperManager;
import com.example.ECommerceApp.entities.concretes.Order;
import com.example.ECommerceApp.entities.concretes.OrderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderProductManager implements OrderProductService {

    private ModelMapperManager modelMapperService;

    @Override
    public List<OrderProduct> getOrderProducts(OrderResponse orderResponse) {
        Order order = modelMapperService.forResponse().map(orderResponse, Order.class);

        return order.getOrderProducts();
    }

}
