package com.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CtrlProduct {

  @GetMapping()
  public List<Category> getCategories() {
    List<Category> categories = new ArrayList<>();

    categories.add(new Category(1, "Lentes", "Lts", 1));
    return categories;
  }

}
