package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductCategoriesService {
    Page<ProductCategoriesModel> getAllProductCategories(Pageable pageable);
    ProductCategoriesModel getProductCategory(Long productCategoryId);
    ProductCategoriesModel createProductCategory(ProductCategoriesModel productCategoriesModel);
    ProductCategoriesModel updateProductCategory(String productCategoryName, ProductCategoriesModel productCategoriesModel);
    void deleteProductCategoryById(Long productCategoryId);
}
