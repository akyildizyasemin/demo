package com.example.ECommerceApp.business.concretes;

import com.example.ECommerceApp.business.abstracts.ShoppingCartService;
import com.example.ECommerceApp.business.requests.UpdateShoppingCartProductRequest;
import com.example.ECommerceApp.business.responses.ProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartProductResponse;
import com.example.ECommerceApp.business.responses.ShoppingCartResponse;
import com.example.ECommerceApp.core.utilities.mappers.ModelMapperService;
import com.example.ECommerceApp.dataAccess.abstracts.ShoppingCartProductRepository;
import com.example.ECommerceApp.dataAccess.abstracts.ShoppingCartRepository;
import com.example.ECommerceApp.dataAccess.abstracts.UserRepository;
import com.example.ECommerceApp.entities.concretes.Product;
import com.example.ECommerceApp.entities.concretes.ShoppingCart;
import com.example.ECommerceApp.entities.concretes.ShoppingCartProduct;
import com.example.ECommerceApp.entities.concretes.User;
import com.example.ECommerceApp.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartManager implements ShoppingCartService {
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartProductRepository shoppingCartProductRepository;
    private ModelMapperService modelMapperService;

    @Override
    public void addProductToCart(int userId, ProductResponse productResponse, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            user.setShoppingCart(shoppingCart);
        }

        Product product = modelMapperService.forResponse().map(productResponse, Product.class);
        user.setshoppingcart.add(product,quantity);

    }


    @Override
    public void removeProductFromCart(int userId, ShoppingCartProductResponse shoppingCartProductResponse) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart != null) {
            List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
            if (shoppingCartProducts != null) {
                ShoppingCartProduct shoppingCartProduct = modelMapperService.forResponse()
                        .map(shoppingCartProductResponse, ShoppingCartProduct.class);
                shoppingCartProducts.remove(shoppingCartProduct);

                shoppingCart.setShoppingCartProducts(shoppingCartProducts);
            }
        }
        userRepository.save(user);
    }

    @Override
    public void updateShoppingCartProductQuantity(int userId, UpdateShoppingCartProductRequest request, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            // Handle the case where the user does not have a shopping cart
            throw new NotFoundException("Shopping cart not found for user with ID: " + userId);
        }

        List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
        if (shoppingCartProducts == null) {
            // Handle the case where the shopping cart does not contain any products
            throw new NotFoundException("No products found in the shopping cart");
        }

        // Find the shopping cart product that matches the given product ID
        ShoppingCartProduct shoppingCartProduct = shoppingCartProducts.stream()
                .filter(product -> product.getId() == request.getProductId())
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product not found in the shopping cart"));

        // Update the quantity of the shopping cart product
        shoppingCartProduct.setQuantity(request.getQuantity());

        // Save the updated shopping cart
        shoppingCartRepository.save(shoppingCart);
    }




    @Override
    public void clearCart(int userId) {
        int userId = userResponse.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart != null) {
            shoppingCart.getShoppingCartProducts().clear();
            shoppingCartRepository.save(shoppingCart);
        }

    }

    @Override
    public ShoppingCartResponse getShoppingCart(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (shoppingCart == null) {
            // Handle the case where the user does not have a shopping cart
            return new ShoppingCartResponse(); // Boş bir sepet dönülebilir
        }

        List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
        if (shoppingCartProducts == null || shoppingCartProducts.isEmpty()) {
            // Handle the case where the shopping cart does not contain any products
            return new ShoppingCartResponse(); // Boş bir sepet dönülebilir
        }

        // Map the ShoppingCartProduct objects to ShoppingCartProductResponse objects
        List<ShoppingCartProductResponse> productResponses = shoppingCartProducts.stream()
                .map(product -> modelMapperService.forResponse().map(product, ShoppingCartProductResponse.class))
                .collect(Collectors.toList());

        // Calculate the total quantity and total price of the shopping cart
        int totalQuantity = productResponses.stream()
                .mapToInt(ShoppingCartProductResponse::getQuantity)
                .sum();
        double totalPrice = productResponses.stream()
                .mapToDouble(ShoppingCartProductResponse::getSubtotal)
                .sum();

        // Create the ShoppingCartResponse object
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        shoppingCartResponse.setCartId(shoppingCart.getCartId());
        shoppingCartResponse.setProducts(productResponses);
        shoppingCartResponse.setTotalQuantity(totalQuantity);
        shoppingCartResponse.setTotalPrice(totalPrice);

        return shoppingCartResponse;
    }

}
