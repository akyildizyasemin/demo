package com.example.ECommerceApp.business.concretes;

import com.example.ECommerceApp.business.abstracts.OrderService;
import com.example.ECommerceApp.business.requests.CreateOrderRequest;
import com.example.ECommerceApp.business.responses.OrderResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperService;
import com.example.ECommerceApp.dataAccess.abstracts.OrderRepository;
import com.example.ECommerceApp.dataAccess.abstracts.UserRepository;
import com.example.ECommerceApp.entities.concretes.Order;
import com.example.ECommerceApp.entities.concretes.User;
import com.example.ECommerceApp.enums.OrderStatus;
import com.example.ECommerceApp.exception.InvalidStateException;
import com.example.ECommerceApp.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private OrderService orderService;
    private ModelMapperService modelMapperService;
    private OrderRepository orderRepository;
    private UserRepository userRepository;


    @Override
    public OrderResponse getOrderById(OrderResponse orderResponse) {
        int orderId = orderResponse.getOrderId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with ID: " + orderId));

        return modelMapperService.forResponse().map(order, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getOrdersByStatus(OrderStatus orderStatus) {
        return orderService.getOrdersByStatus(orderStatus);
    }


    @Override
    public List<OrderResponse> getOrdersByUserId(UserResponse userResponse) {
        int userId = userResponse.getId();
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = modelMapperService.forResponse().map(order, OrderResponse.class);
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }


    @Override
    public void createOrder(CreateOrderRequest createOrderRequest) {
        Date orderDate = new Date();  // Şu anki tarih ve saat
        OrderStatus orderStatus = OrderStatus.CREATED;  // Yeni sipariş durumu

        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        order.setOrderDate(orderDate);
        order.setOrderStatus(orderStatus);

        this.orderRepository.save(order);
    }

    @Override
    public void cancelOrder(OrderResponse orderResponse) {
        int orderId = orderResponse.getOrderId();
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        } else {
            throw new NotFoundException("Order not found with id: " + orderId);
        }
    }

    @Override
    public void updateOrderAddressByUserId(int userId, String newAddress) {
        User user = UserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        // Kullanıcının sipariş adres bilgisini güncelle
        user.setAddress(newAddress);

        // Kullanıcıyı veritabanına kaydet
        userRepository.save(user);
    }

    @Override
    public void markOrderAsShipped(OrderResponse orderResponse) {
        int orderId = orderResponse.getOrderId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.CREATED) {
            throw new InvalidStateException("Cannot mark order as shipped. Invalid order status.");
        }

        order.setOrderStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }


    @Override
    public void markOrderAsDelivered(OrderResponse orderResponse) {
        int orderId = orderResponse.getOrderId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.SHIPPED) {
            throw new InvalidStateException("Cannot mark order as delivered. Invalid order status.");
        }

        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
    }


}
