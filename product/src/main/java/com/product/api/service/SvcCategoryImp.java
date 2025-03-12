package com.product.api.service;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.product.api.repository.RepoCategory;
import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.exception.ApiException;

import org.springframework.http.ResponseEntity;

@Service
public class SvcCategoryImp implements SvcCategory {
	@Autowired
	RepoCategory repo;
	
	@Override
	public ResponseEntity<List<Category>> getCategories() {
		try {		
			return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar la base de datos");
		}
	}
	
	public ResponseEntity<List<Category>> getActiveCategories() {
		return new ResponseEntity<>( repo.getActiveCategories(), HttpStatus.OK);
	}
	
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in) {
		repo.insertCategory(in.getCategory(), in.getTag());
		return new ResponseEntity<>(new ApiResponse("La categoría ha sido registrada"), HttpStatus.CREATED);
	}
	
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id) {
		if(repo.findById(id).isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
		
		repo.updateCategory(id, in.getCategory(), in.getTag());
		return new ResponseEntity<>(new ApiResponse("La categoría ha sido actualizada"), HttpStatus.OK);
	}
	
	public ResponseEntity<ApiResponse> enableCategory(Integer id) {
		if(repo.findById(id).isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
		
		repo.updateCategoryStatus(id, 1);
		return new ResponseEntity<>(new ApiResponse("La región ha sido activada"), HttpStatus.OK);
	}
	
	public ResponseEntity<ApiResponse> disableCategory(Integer id) {
		if(repo.findById(id).isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
		
		repo.updateCategoryStatus(id, 0);
		return new ResponseEntity<>(new ApiResponse("La región ha sido desactivada"), HttpStatus.OK);
	}

}
