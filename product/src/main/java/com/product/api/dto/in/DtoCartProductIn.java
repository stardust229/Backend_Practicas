package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DtoCartProductIn {
	@JsonProperty("product_id")
	@NotNull(message="El productId es obligatorio")
	private Integer productId;
	
	
	@JsonProperty("cart_id")
	@NotNull(message="El cartId es obligatorio")
	private String cartId;
	
	
	@JsonProperty("amount")
	@NotNull(message="La cantidad de productos es obligatorio")
	private Integer amount;


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getCartId() {
		return cartId;
	}


	public void setCartId(String cartId) {
		this.cartId = cartId;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
