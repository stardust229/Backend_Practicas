package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoCartProductIn;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoCartProductOut;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.service.SvcCart;
import com.product.api.service.SvcProduct;
import com.product.api.commons.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cart", description = "Carrito de compras")
@RestController
@RequestMapping("/cart")
public class CtrlCart {

	@Autowired
	SvcCart svc;
	
	@PostMapping
	public ResponseEntity<DtoCartProductOut> addCartProduct(@Valid @RequestBody DtoCartProductIn in, Integer idProduct){
		return svc.addCartProduct(in, idProduct);
	}

	@GetMapping
	public ResponseEntity<List<DtoProductListOut>>getCartProduct(){
		return svc.getCartProducts();
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> removeCartProduct(@Valid @RequestBody DtoCartProductIn in, Integer idProduct) {
		return svc.removeCartProduct(in, idProduct);
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse> clearCart(){
		return svc.clearCart();
	}

}

