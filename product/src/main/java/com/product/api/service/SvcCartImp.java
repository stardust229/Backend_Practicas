package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.commons.mapper.MapperProduct;
import com.product.api.dto.in.DtoCartProductIn;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoCartProductOut;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;
import com.product.api.repository.RepoProduct;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;


public class SvcCartImp implements SvcCart{
	//@Autowired
	//RepoCart repoCart;
	@Autowired
	RepoProduct repoProduct;
	
	@Value("${app.upload.dir}")
	private String uploadDir;

	@Override
	public ResponseEntity<DtoCartProductOut> addCartProduct(DtoCartProductIn in, Integer idProduct) {
		try {
			validateProductId(idProduct);
			DtoProductOut product = repoProduct.getProduct(idProduct);
			
			if(product == null )
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			
			if (product.getStock() < in.getAmount()) {
	            throw new ApiException(HttpStatus.CONFLICT,"Stock insuficiente"); //aqui no se si es conflict o que exception
	        }
			
			//falta agregarlo al carrito
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<List<DtoProductListOut>> getCartProducts() {
		try {
			List<Product> products = repoCart.findAll(); //falta implementar el repo del carrito
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> removeCartProduct() {

	}

	@Override
	public ResponseEntity<ApiResponse> clearCart() {

	}

	private void validateProductId(Integer id) {
		try {
			if(repoProduct.findById(id).isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

}
