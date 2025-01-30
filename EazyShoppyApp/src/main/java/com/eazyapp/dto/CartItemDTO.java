package com.eazyapp.dto;

import com.eazyapp.model.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private Long userId;

    public CartItemDTO() {}

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.productId = cartItem.getProduct().getProductId();
        this.quantity = cartItem.getQuantity();
        this.userId = cartItem.getUser() != null ? cartItem.getUser().getId() : null;
    }
}