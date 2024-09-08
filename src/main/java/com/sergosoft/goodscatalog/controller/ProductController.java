package com.sergosoft.goodscatalog.controller;

import com.sergosoft.goodscatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    TODO
    Implement the presentation in the view of the controller (@Controller)
    and templates (Thymeleaf) with minimal functionality sufficient
    for demonstrating business logic components.
     */
}
