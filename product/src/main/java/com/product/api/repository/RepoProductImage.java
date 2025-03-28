package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.product.api.entity.ProductImage;

import jakarta.transaction.Transactional;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {
	
	@Query(value="SELECT * FROM product_image WHERE product_id=:product_id", nativeQuery=true)
	List<ProductImage> findByProductId(Integer product_id);
	
	@Query(value="SELECT * FROM product_image WHERE product_image_id=:product_image_id", nativeQuery=true)
	ProductImage findByProductImageId(Integer product_image_id);
	
	@Modifying
	@Transactional
    @Query(value="DELETE FROM product_image WHERE product_image_id=:product_image_id", nativeQuery=true)
    void deleteProductImageById(Integer product_image_id);
}
