package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class DtoProductImageIn {
	@JsonProperty("product_id")
	@NotNull(message="El productId es obligatorio")
	private Integer productId;
	
	
	@JsonProperty("image")
	@NotNull(message="El image es obligatorio")
	private String image;


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
}
