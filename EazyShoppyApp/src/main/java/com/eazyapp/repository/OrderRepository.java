package com.eazyapp.repository;

import com.eazyapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find an order by its ID
    @Query("SELECT o FROM Order o WHERE o.orderId = ?1")
    Optional<Order> findByOrderId(long orderId);

    // Find all orders by user ID
    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    List<Order> findByUserId(long userId);

    // Find all orders by order status
    @Query("SELECT o FROM Order o WHERE o.orderstatus = ?1")
    List<Order> findByOrderStatus(String orderStatus);

    // Find all orders placed on a specific date
    @Query("SELECT o FROM Order o WHERE o.orderDate = ?1")
    List<Order> findByOrderDate(LocalDate orderDate);

    // Find all orders with a total amount greater than or equal to a given value
    @Query("SELECT o FROM Order o WHERE o.totalAmount >= ?1")
    List<Order> findByTotalAmountGreaterThanEqual(double totalAmount);

    // Find all orders with a total amount less than or equal to a given value
    @Query("SELECT o FROM Order o WHERE o.totalAmount <= ?1")
    List<Order> findByTotalAmountLessThanEqual(double totalAmount);
}