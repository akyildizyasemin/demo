package com.example.ECommerceApp.entities.concretes;

import com.example.ECommerceApp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    @OneToMany(mappedBy = "productId")
//    private List<OrderProduct> orderProducts;

//    @OneToOne
//    @JoinColumn(name = "payment_id", nullable = false)
//    private Payment payment;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
