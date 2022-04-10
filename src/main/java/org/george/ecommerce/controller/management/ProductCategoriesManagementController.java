package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.service.ProductCategoriesServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/product-categories")
public class ProductCategoriesManagementController {
    final ProductCategoriesServiceImpl productCategoriesService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<ProductCategoriesModel>> getAllProductCategories(Pageable pageable) {
        return ResponseEntity.ok().body(productCategoriesService.getAllProductCategories(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{productCategoryId}")
    public ResponseEntity<?> getProductCategoryById(
            @PathVariable("productCategoryId") Long productCategoryId) {
        return ResponseEntity.ok().body(productCategoriesService.getProductCategory(productCategoryId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createProductCategory(
            @RequestBody
            @Valid ProductCategoriesModel productCategoriesModel) {
        return ResponseEntity.ok().body(productCategoriesService.createProductCategory(productCategoriesModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{productCategoryName}")
    public ResponseEntity<?> updateProductCategory(
            @PathVariable("productCategoryName") String productCategoryName,
            @RequestBody
            @Valid ProductCategoriesModel productCategoriesModel) {
        return ResponseEntity.ok().body(productCategoriesService.updateProductCategory(productCategoryName, productCategoriesModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productCategoryId}")
    public ResponseEntity<?> deleteProductCategory(
            @PathVariable("productCategoryId") Long productCategoryId) {
        productCategoriesService.deleteProductCategoryById(productCategoryId);
        return ResponseEntity.ok().body("Deleted");
    }
}
