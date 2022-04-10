package org.george.ecommerce.controller.store;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.views.ProductCategoriesModelView;
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

    //TODO - fix Query when Categories available
    @GetMapping("/category")
    public ResponseEntity<Page<ProductCategoriesModelView>> findProductsModelsByProductNameAndAndProductCategories(Pageable pageable){
        return ResponseEntity.ok().body(productsService.findByProductAndCategory(pageable));
    }

    @GetMapping("/search")
    public Page<ProductsModel> findAllProductsByFilter(@Valid @RequestBody ProductsModel productsModel, Pageable pageable) {
        return productsService.getAllProductsByFilter(productsModel, pageable);
    }
}
