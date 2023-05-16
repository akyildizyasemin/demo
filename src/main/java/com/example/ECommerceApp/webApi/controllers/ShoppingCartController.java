package com.example.ECommerceApp.webApi.controllers;

import com.example.ECommerceApp.business.abstracts.ShoppingCartService;
import com.example.ECommerceApp.business.requests.UpdateShoppingCartProductRequest;
import com.example.ECommerceApp.business.requests.create.UpdateProductRequest;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperService;
import com.example.ECommerceApp.entities.concretes.Product;
import com.example.ECommerceApp.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);


    @PostMapping("/users/{userId}/cart/products")
    public ResponseEntity<Void> addProductToCart(@PathVariable int userId, @RequestBody ProductResponse productResponse, @RequestParam int quantity) {
        try {
            userService.addProductToCart(userId, productResponse, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/users/{userId}/cart/products")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable int userId, @RequestBody ShoppingCartProductResponse shoppingCartProductResponse) {
        try {
            shoppingCartService.removeProductFromCart(userId, shoppingCartProductResponse);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/{userId}/cart/products/quantity")
    public ResponseEntity<Void> updateShoppingCartProductQuantity(@PathVariable int userId, @RequestBody UpdateShoppingCartProductRequest request, int quantity) {
        Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
        logger.info("Updating product quantity in shopping cart for user with ID: " + userId);

        try {
            shoppingCartService.updateShoppingCartProductQuantity(userId, request, quantity);
            logger.info("Product quantity updated successfully in shopping cart for user with ID: " + userId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            logger.error("Error updating product quantity in shopping cart for user with ID: " + userId);
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/users/{userId}/cart")
    public ResponseEntity<ShoppingCartResponse> getShoppingCart(@PathVariable int userId) {
        try {
            logger.info("Getting shopping cart for user with ID: {}", userId);

            ShoppingCartResponse shoppingCartResponse = shoppingCartService.getShoppingCart(userId);
            logger.info("Shopping cart retrieved successfully");

            return ResponseEntity.ok(shoppingCartResponse);
        } catch (NotFoundException e) {
            logger.error("Shopping cart not found for user with ID: {}", userId);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("An error occurred while retrieving shopping cart for user with ID: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/users/{userId}/cart")
    public ResponseEntity<Void> clearCart(@PathVariable int userId) {
        logger.info("Clearing cart for user with ID: " + userId);

        try {
            shoppingCartService.clearCart(userId);
            logger.info("Cart cleared successfully for user with ID: " + userId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            logger.error("User not found with ID: " + userId);
            return ResponseEntity.notFound().build();
        }
    }


}
