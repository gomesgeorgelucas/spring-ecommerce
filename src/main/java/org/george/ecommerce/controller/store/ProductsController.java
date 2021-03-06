package org.george.ecommerce.controller.store;

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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/store/products")
public class ProductsController {
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

    @GetMapping("/{productName}")
    public ResponseEntity<?> findProductsByProductName(
            @PathVariable("productName") String productName) {
        return ResponseEntity.ok().body(productsService.findProductByProductName(productName));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getProductsByCategoryName(
            @PathVariable("categoryName") String categoryName,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.findAllByProductCategoriesIn(categoryName, pageable));
    }

    @GetMapping("/category/{categoryName}/{productName}")
    public ResponseEntity<?> findAllByProductCategoriesInAndProductNameContaining(
            @PathVariable("categoryName") String categoryName,
            @PathVariable("productName") String productName,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.findAllByProductCategoriesInAndProductNameContaining(categoryName, productName, pageable));
    }

    //TODO -
    @GetMapping("/searchBy/{categoryInfo}/{productInfo}")
    public ResponseEntity<?> findAllProductsByFilter(
            @PathVariable("categoryInfo") String categoryInfo,
            @PathVariable("productInfo") String productInfo,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.getAllProductsByFilter(categoryInfo, productInfo, pageable));
    }

    @GetMapping("/searchBy")
    public ResponseEntity<?> findAllProductsByFilter(
            @RequestBody
            @Valid ProductsModel productsModel,
            Pageable pageable) {
        return ResponseEntity.ok().body(productsService.getAllProductsByFilter(productsModel, pageable));
    }


}
