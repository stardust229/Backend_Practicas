package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {
	
	@Query(value ="SELECT * FROM product WHERE product_id=:product_id", nativeQuery = true)
	Product getProductRaw(Integer product_id);
	
	@Query(value = "SELECT p.product_id, p.gtin, p.product, p.description, p.price, p.stock, p.category_id, p.status, i.image "
			+ "FROM product p "
			+ "INNER JOIN product_image i ON i.product_id = p.product_id "
				+ "WHERE p.product_id = :product_id;", nativeQuery = true)
	DtoProductOut getProduct(Integer product_id);

}
