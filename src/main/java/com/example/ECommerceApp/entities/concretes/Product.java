package com.example.ECommerceApp.entities.concretes;
import com.example.ECommerceApp.enums.ProductMaterial;
import com.example.ECommerceApp.enums.RingSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "tb_category", referencedColumnName = "categoryId", nullable = false)
    private Category category;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ring_size", nullable = false)
    private RingSize ringSize;


    @ElementCollection
    @CollectionTable(name="product_size", joinColumns=@JoinColumn(name="productId"))
    @MapKeyColumn(name="size")
    @Column(name="stock_number")
    private Map<RingSize, Integer> sizeStockMap;

    @DecimalMin(value = "0.01", inclusive = true)
    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "image_url", nullable = false)
    private String productImageUrl;

    @Column(name = "product_details", nullable = false)
    private String productDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_material", nullable = false)
    private ProductMaterial productMaterial;
//
//    @OneToMany(mappedBy = "tb_product")
//    private List<OrderProduct> orderProducts;

}

