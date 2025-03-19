package com.product.api.service;

import org.springframework.http.ResponseEntity;
import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;

public interface SvcProductImage {
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);
}
