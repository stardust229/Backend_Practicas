package com.product.api.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {
	@Autowired
	RepoProductImage repo;
	
	@Autowired
	RepoProduct repoProduct;
	
	@Value("${app.upload.dir}")
	private String uploadDir;

	
	@Override
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in) {
		try {
			
			try {
				// Eliminar el prefijo "data:image/png;base64," si existe
				if (in.getImage().startsWith("data:image")) {
					int commaIndex = in.getImage().indexOf(",");
					if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
					}
				}

				// Decodifica la cadena Base64 a bytes
				byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
	
	
				// Genera un nombre único para la imagen (se asume extensión PNG)
				String fileName = UUID.randomUUID().toString() + ".png";
	
	
				// Construye la ruta completa donde se guardará la imagen
				Path imagePath = Paths.get(uploadDir, "img", "product", fileName);
				
				// Asegurarse de que el directorio exista
				Files.createDirectories(imagePath.getParent());
	
	
				// Escribir el archivo en el sistema de archivos
				Files.write(imagePath, imageBytes);
							
				
				// Validamos que exista el producto
				Product product = repoProduct.getProductRaw(in.getProductId());
				
				if(product == null)
					throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
				
				
				// Crear la entidad CustomerImage y guardar la URL en la base de datos
				ProductImage productImage = new ProductImage();
				productImage.setProductId(in.getProductId());
				
				productImage.setImage("/img/product/" + fileName);
				
				productImage.setStatus(1); 


				// Guardar la ruta de la imagen
				repo.save(productImage);

			    // code
			    return new ResponseEntity<>(new ApiResponse("La imagen del producto ha sido actualizada"), HttpStatus.OK);
			}catch (DataAccessException e) {
			    throw new DBAccessException(e);
			}
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}
	}


	@Override
	public ResponseEntity<ApiResponse> deleteProductImage(Integer id) {
		try {
		    // Buscar la imagen en la base de datos
		    ProductImage productImage = repo.findById(id)
		        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "La imagen no existe"));

		    // Construiye la ruta completa de donde se eliminara la imagen

		    Path filePath = Paths.get(uploadDir, "img", "product", productImage.getImage());

		    // 3. Eliminar el archivo físico
		    try {
		        Files.deleteIfExists(filePath);
		    } catch (IOException e) {
		        System.err.println("Advertencia: No se pudo eliminar el archivo físico: " + filePath);
		    }

		    // 4. Eliminar el registro de la base de datos
		    repo.deleteById(id);

		    return new ResponseEntity<>(new ApiResponse("La imagen ha sido eliminada"), HttpStatus.OK);
		    
		} catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}
	}
	
	

}
