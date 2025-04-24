package com.product.api.service;

import org.springframework.http.ResponseEntity;
import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;

import jakarta.validation.Valid;

public interface SvcProductImage {
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in);
	public ResponseEntity<ApiResponse> deleteProductImage(Integer id);
}
