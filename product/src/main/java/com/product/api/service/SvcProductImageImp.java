package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.repository.RepoProductImage;
import com.product.exception.DBAccessException;

public class SvcProductImageImp implements SvcProductImage {
	@Autowired
	RepoProductImage repo;
	@Override
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in) {
		try {
		    // code
		    return new ResponseEntity<>(new ApiResponse("La imagen del producto ha sido actualizada"), HttpStatus.OK);
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}
	}

}
