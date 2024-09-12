package com.sergosoft.goodscatalog.controller;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));
        return "product";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products.stream().map(productMapper::toDto));
        return "products";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductCreationRequest());
        return "admin/product_form";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductCreationRequest creationRequest) {
        Product product = productService.createProduct(creationRequest);
        return "redirect:/products/" + product.getId();
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{productId}")
    public String editProduct(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));
        return "admin/product_update_form";
    }

    @PostMapping("/edit/{productId}")
    public String updateProduct(
            @PathVariable Long productId,
            @Valid @ModelAttribute("product") ProductDto productDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/product_update_form";
        }

        productService.updateProduct(productDto.getId(), productMapper.toEntity(productDto));
        return "redirect:/products/all";
    }

}
