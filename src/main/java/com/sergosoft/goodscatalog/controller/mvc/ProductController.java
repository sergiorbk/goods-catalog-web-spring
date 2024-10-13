package com.sergosoft.goodscatalog.controller.mvc;

import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import com.sergosoft.goodscatalog.model.user.UserRole;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public String getProductFilteredProductsByPage(
            Model model,
            Authentication auth,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @Valid ProductFilter productFilter,
            PagedResourcesAssembler<ProductDto> pagedResourcesAssembler) {

        log.info("Received request to show filtered products page");
        Page<Product> productsPage = productService.getFilteredProductsByPage(page, pageSize, productFilter);
        model.addAttribute("products", productsPage.toList());

        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ADMIN.name()));
        model.addAttribute("isAdmin", isAdmin);
        log.info("Returning products (page {}, pageSize = {}) for a simple user:{}",page, pageSize, isAdmin);
        return "products";
    }

    @GetMapping("/{productId}")
    public String showProductById(Model model, @PathVariable Long productId) {
        log.info("Received request to show product with ID: {}", productId);

        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));

        log.info("Returning product view for product with ID: {}", productId);

        return "product";
    }

    @GetMapping("/moderate/create")
    public String showCreateProductForm(Model model) {
        log.info("Received request to show product creation form");

        model.addAttribute("product", new ProductRequest());

        log.info("Returning product creation form view");

        return "admin/product_form";
    }

    @GetMapping("/moderate/{productId}/edit")
    public String showProductEditForm(@PathVariable Long productId, Model model) {
        log.info("Received request to show edit form for product with ID: {}", productId);

        Product product = productService.getProductById(productId);
        model.addAttribute("product", productMapper.toDto(product));

        log.info("Returning product edit form view for product with ID: {}", productId);

        return "admin/product_update_form";
    }

    @PostMapping("/moderate/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductRequest creationRequest) {
        log.info("Received request to create product with name: {}", creationRequest.getName());

        Product product = productService.createProduct(creationRequest);

        log.info("Product created with ID: {}", product.getId());

        return "redirect:/products/" + product.getId();
    }

    @PutMapping("/moderate/{productId}")
    public String updateProduct(
            @PathVariable Long productId,
            @Valid @ModelAttribute("product") ProductRequest productRequest,
            BindingResult result
    ) {
        log.info("Received request to update product with ID: {}", productId);

        if (result.hasErrors()) {
            log.warn("Validation errors occurred during product update: {}", result.getAllErrors());
            return "admin/product_update_form";
        }

        productService.updateProduct(productId, productRequest);

        log.info("Product updated with ID: {}", productId);

        return "redirect:/products";
    }

    @GetMapping("/moderate/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        log.info("Received request to delete product with ID: {}", productId);

        productService.deleteProduct(productId);

        log.info("Product deleted with ID: {}", productId);

        return "redirect:/products";
    }
}
