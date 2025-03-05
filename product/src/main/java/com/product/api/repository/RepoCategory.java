package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

public interface RepoCategory extends JpaRepository<Category, Integer> {

	@Query(value="SELECT * FROM category ORDER BY category", nativeQuery=true)
	List<Category> getCategories();
	
	@Query(value ="SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> getActiveCategories();
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
	void insertCategory(String category, String tag);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id", nativeQuery = true)
	void updateCategory(@Param("category_id") Integer category_id, @Param("category") String category, @Param("tag") String tag);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
	void updateCategoryStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);

}
