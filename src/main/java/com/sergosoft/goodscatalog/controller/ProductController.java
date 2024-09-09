package com.sergosoft.goodscatalog.controller;


import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    /*
    TODO
    Implement the presentation in the view of the controller (@Controller)
    and templates (Thymeleaf) with minimal functionality sufficient
    for demonstrating business logic components.
     */
}
