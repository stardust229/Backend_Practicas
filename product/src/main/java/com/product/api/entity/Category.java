package com.product.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

  @Id
  private Integer category_id;
  private String category;
  private String tag;
  private Integer status;
  
  public Category() {
	  
  }

  public Category(int id, String category, String tag, int status) {
    this.category_id = id; 
    this.category = category;
    this.tag = tag;
    this.status = status;
  }
  
  public void setCategoryId(Integer categoryId) {
	this.category_id = categoryId;
  }
	
  public void setCategory(String category){
	this.category = category;
  }
  
  public void setTag(String tag){
  	this.tag = tag;
  }
  
  public void setStatus(Integer status){
  	this.status = status;
  }
  
  public Integer getCategoryId(){
	return category_id;
  }
  
  public String getCategory(){
  	return category;
  }
	
  public String getTag(){
    return tag;
  }
	
  public Integer getStatus(){
    return status;
  }
	
  @Override
  public String toString(){
    return "{ id: " + category_id + ", nombre: \"" + category + "\", tag: \"" + tag + "\", estatus: " + status + " }";
  }

}
