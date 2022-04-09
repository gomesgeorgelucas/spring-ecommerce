package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.service.ProductCategoriesServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/product-categories")
public class ProductCategoriesManagementController {
    final ProductCategoriesServiceImpl productCategoriesService;

    @GetMapping
    public ResponseEntity<Page<ProductCategoriesModel>> getAllProductCategories(Pageable pageable) {
        return ResponseEntity.ok().body(productCategoriesService.getAllProductCategories(pageable));
    }

    @GetMapping("/id/{productCategoryId}")
    public ResponseEntity<?> getProductCategoryById(@PathVariable("productCategoryId") Long productCategoryId) {
        return ResponseEntity.ok().body(productCategoriesService.getProductCategory(productCategoryId));
    }

    @PostMapping
    public ResponseEntity<?> createProductCategory(
            @RequestBody ProductCategoriesModel productCategoriesModel) {
        return ResponseEntity.ok().body(productCategoriesService.createProductCategory(productCategoriesModel));
    }

    @PutMapping("/name/{productCategoryName}")
    public ResponseEntity<?> updateProductCategory(
            @PathVariable("productCategoryName") String productCategoryName,
            @RequestBody ProductCategoriesModel productCategoriesModel) {
        return ResponseEntity.ok().body(productCategoriesService.updateProductCategory(productCategoryName, productCategoriesModel));
    }

    @DeleteMapping("/id/{productCategoryId}")
    public ResponseEntity<?> deleteProductCategory(
            @PathVariable("productCategoryId") Long productCategoryId) {
        productCategoriesService.deleteProductCategoryById(productCategoryId);
        return ResponseEntity.ok().body("Deleted");
    }
}
