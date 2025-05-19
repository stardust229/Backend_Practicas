package com.product.api.dto.out;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.api.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cartProduct")
public class DtoCartProductOut {
	
	@Id
	@JsonProperty("cart_id")
	private Integer cart_id;
	
	@JsonProperty("product_id")
	private Integer product_id;
		
	@JsonProperty("product_name")
	private String productName;
		
	@JsonProperty("quantity")
	private Integer amount;
	
	@JsonProperty("total")
	private Double total;
	
	@JsonProperty("all_products")
	@Transient
	private List<Product> allProducts;
	
	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}

	
}