package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoCartProductIn;
import com.product.api.dto.out.DtoCartProductOut;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.commons.dto.ApiResponse;

public interface SvcCart {

	public ResponseEntity<DtoCartProductOut> addCartProduct(DtoCartProductIn in, Integer idProduct);
	public ResponseEntity<List<DtoProductListOut>> getCartProducts();
	public ResponseEntity<ApiResponse> removeCartProduct(DtoCartProductIn in, Integer idProduct);
	public ResponseEntity<ApiResponse> clearCart();

}
