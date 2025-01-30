package com.eazyapp.repository;

import com.eazyapp.model.Cart;
import com.eazyapp.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {

    @Query("select pi from ProductImage pi where pi.product.id = :productId")
    List<ProductImage> findByProductId(Long productId);

}
