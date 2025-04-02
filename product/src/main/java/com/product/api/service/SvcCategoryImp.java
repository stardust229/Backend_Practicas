package com.product.api.service;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.product.api.repository.RepoCategory;
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
}
