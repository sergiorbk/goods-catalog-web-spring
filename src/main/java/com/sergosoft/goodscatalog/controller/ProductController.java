package com.sergosoft.goodscatalog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import com.sergosoft.goodscatalog.model.user.UserRole;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    //
    // GET methods to return HTML pages
    //
    @GetMapping({"", "/", "/all"})
    public String showAllProducts(Model model, Authentication auth) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("isAdmin", auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ADMIN.name())));
        return "products";
    }

    @GetMapping("/{productId}")
    public String showProductById(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));
        return "product";
    }

    @GetMapping("/moderate/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductCreationRequest());
        return "admin/product_form";
    }

    @GetMapping("/moderate/{productId}/edit")
    public String showProductEditForm(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));
        return "admin/product_update_form";
    }

    //
    // Executive methods (POST, PUT, DELETE and/or their GET representations for MVC)
    //
    @PostMapping("/moderate/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductCreationRequest creationRequest) {
        Product product = productService.createProduct(creationRequest);
        return "redirect:/products/" + product.getId();
    }

    @PutMapping("/moderate/{productId}")
    public String updateProduct(
            @PathVariable Long productId,
            @Valid @ModelAttribute("product") ProductDto productDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/product_update_form";
        }
        productDto.setId(productId);
        productService.updateProduct(productDto.getId(), productMapper.toEntity(productDto));
        return "redirect:/products/all";
    }

    @GetMapping("/moderate/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/products/all";
    }
}
