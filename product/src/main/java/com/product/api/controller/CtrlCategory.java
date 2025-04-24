package com.product.api.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Category", description = "Categorías")
@RestController
@RequestMapping("/category")
public class CtrlCategory {
	
	@Autowired
	SvcCategory svc;

  @GetMapping()
  public ResponseEntity<List<Category>> getCategories() {

    return svc.getCategories();
  }
  
  @Operation(summary = "Consultar categorías", description = "Retorna todas las categorías activas registradas en el sistema")
  @GetMapping("/active")
  public ResponseEntity<List<Category>> getActiveCategories(){
  	return svc.getActiveCategories();
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult){
	  if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

  	return svc.createCategory(in);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult){
  	if(bindingResult.hasErrors())
  		throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
  	return svc.updateCategory(in, id);
  }
  
  @PatchMapping("/{id}/enable")
  public ResponseEntity<ApiResponse> enableCategory(@PathVariable("id") Integer id){
  	return svc.enableCategory(id);
  }
  	
  @PatchMapping("/{id}/disable")
  public ResponseEntity<ApiResponse> disableCategory(@PathVariable("id") Integer id){
  	return svc.disableCategory(id);
  }


}

