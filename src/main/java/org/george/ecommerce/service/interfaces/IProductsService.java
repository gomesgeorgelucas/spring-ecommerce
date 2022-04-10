package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.views.ProductCategoriesModelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductsService {
    Page<ProductsModel> getAllProducts(Pageable pageable);

    ProductsModel getProductById(Long productId);

    ProductsModel createProduct(ProductsModel productsModel);

    ProductsModel updateProduct(Long productId, ProductsModel productsModel);

    void deleteProductById(Long productId);

    Page<ProductsModel> getAllProductsByFilter(ProductsModel productsModel, Pageable pageable);

    Page<ProductCategoriesModelView> findByProductAndCategory(Pageable pageable);

    ProductsModel findProductByProductName(String productName);

    Page<ProductsModel> getProductsByCategoryName(String categoryName, Pageable pageable);
}
