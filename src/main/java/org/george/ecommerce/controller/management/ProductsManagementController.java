package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.service.ProductsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/products")
public class ProductsManagementController {
    private final ProductsServiceImpl productsService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllProducts(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "productUnitPrice", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "productName", direction = Sort.Direction.ASC)}) Pageable pageable) {
        Page<ProductsModel> products = productsService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(
            @PathVariable("productId") Long productId) {
        return ResponseEntity.ok().body(productsService.getProductById(productId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getProductsByCategoryName(
            @PathVariable("categoryName") String categoryName,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.findAllByProductCategoriesIn(categoryName, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category/{categoryName}/{productName}")
    public ResponseEntity<?> findAllByProductCategoriesInAndProductNameContaining(
            @PathVariable("categoryName") String categoryName,
            @PathVariable("productName") String productName,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.findAllByProductCategoriesInAndProductNameContaining(categoryName, productName, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/searchBy")
    public ResponseEntity<?> findAllProductsByFilter(
            @RequestBody
            @Valid ProductsModel productsModel,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.getAllProductsByFilter(productsModel, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestBody
            @Valid ProductsModel productsModel) {
        ProductsModel product = productsService.createProduct(productsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody
            @Valid ProductsModel productsModel) {
        return ResponseEntity.ok(productsService.updateProduct(productId, productsModel));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("productId") Long productId) {
        productsService.deleteProductById(productId);
        return ResponseEntity.ok().body("Deleted");
    }
}
