package com.product.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class DtoCategoryIn {
	@JsonProperty("category")
	@NotNull(message="La categoría es obligatoria")
	private String category;
		
	@JsonProperty("tag")
	@NotNull(message="El tag es obligatorio")
	private String tag;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
