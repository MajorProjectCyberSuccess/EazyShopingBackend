package com.eazyapp.repository;

import com.eazyapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find a cart by its ID
    @Query("SELECT c FROM Cart c WHERE c.cartId = ?1")
    Optional<Cart> findByCartId(long cartId);

    // Find all carts by item ID
    @Query("SELECT c FROM Cart c WHERE c.itemId = ?1")
    List<Cart> findByItemId(long itemId);

    // Find all carts with a total amount greater than or equal to a given value
    @Query("SELECT c FROM Cart c WHERE c.totalAmount >= ?1")
    List<Cart> findByTotalAmountGreaterThanEqual(double totalAmount);

    // Find all carts with a total amount less than or equal to a given value
    @Query("SELECT c FROM Cart c WHERE c.totalAmount <= ?1")
    List<Cart> findByTotalAmountLessThanEqual(double totalAmount);

    // Find all carts by quantity
    @Query("SELECT c FROM Cart c WHERE c.quantity = ?1")
    List<Cart> findByQuantity(int quantity);
}