package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.dto.ProductCategoryDTO;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.service.ProductsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/products")
public class ProductsManagementController {
    final ProductsServiceImpl productsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllProducts(@PageableDefault(size = 5)
                                                 @SortDefault.SortDefaults({
                                                         @SortDefault(sort = "productUnitPrice", direction = Sort.Direction.ASC),
                                                         @SortDefault(sort = "productName", direction = Sort.Direction.ASC)})
                                                         Pageable pageable) {
        Page<ProductsModel> products = productsService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok().body(productsService.getProductById(productId));
    }

    //TODO - fix Query when Categories available
    @GetMapping("/and-category")
    public Page<ProductCategoryDTO> findByProductAndCategory(Pageable pageable){
        return productsService.findByProductAndCategory(pageable);
    }

    @GetMapping("/search")
    public Page<ProductsModel> findAllProductsByFilter(@RequestBody ProductsModel productsModel, Pageable pageable) {
        return productsService.getAllProductsByFilter(productsModel, pageable);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductsModel productsModel) {
        ProductsModel product = productsService.createProduct(productsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/id/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId, @RequestBody @Valid ProductsModel productsModel) {
        return ResponseEntity.ok(productsService.updateProduct(productId, productsModel));
    }

    @DeleteMapping("/id/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productsService.deleteProductById(productId);
        return ResponseEntity.ok().body("Deleted");
    }
}
