package com.e_commerce.category_repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
	
}

