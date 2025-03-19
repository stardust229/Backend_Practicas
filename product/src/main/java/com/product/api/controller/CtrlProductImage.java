package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.service.SvcProductImage;
import com.product.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-image")
public class CtrlProductImage {
	
    @Autowired
    SvcProductImage svc;
	
    @PostMapping
    public ResponseEntity<ApiResponse> createCustomerImage(@Valid @RequestBody DtoProductImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        	throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
        return svc.uploadProductImage(in);
    }
}
