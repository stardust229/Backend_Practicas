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

}
