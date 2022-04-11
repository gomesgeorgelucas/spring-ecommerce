package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.views.ProductCategoriesModelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IProductsService {
    Page<ProductsModel> getAllProducts(Pageable pageable);

    ProductsModel getProductById(Long productId);

    ProductsModel createProduct(ProductsModel productsModel);

    ProductsModel updateProduct(Long productId, ProductsModel productsModel);

    void deleteProductById(Long productId);

    Page<ProductsModel> getAllProductsByFilter(ProductsModel productsModel, Pageable pageable);

    ProductsModel findProductByProductName(String productName);

    Page<Set<ProductsModel>> findAllByProductCategoriesIn(String categoryName, Pageable pageable);

    Page<Set<ProductsModel>> findAllByProductCategoriesInAndProductNameContaining(String categoryName, String productName, Pageable pageable);
}
