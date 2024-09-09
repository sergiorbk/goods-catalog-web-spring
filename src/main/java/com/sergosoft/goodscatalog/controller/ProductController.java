package com.sergosoft.goodscatalog.controller;


import com.sergosoft.goodscatalog.dto.ProductDto;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/product")
    public String showCreateProductForm(Model model) {
        // TODO Create ProductDto instead of Product
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/product_form";
    }

    @PostMapping("/product")
    public String createProduct(@ModelAttribute("product") Product product) {
        // TODO Add saving imgUrls as list
        productService.createProduct(product);
        return "product";
    }

    /*
    TODO
    Implement the presentation in the view of the controller (@Controller)
    and templates (Thymeleaf) with minimal functionality sufficient
    for demonstrating business logic components.
     */
}
