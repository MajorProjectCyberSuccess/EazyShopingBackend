package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

}
