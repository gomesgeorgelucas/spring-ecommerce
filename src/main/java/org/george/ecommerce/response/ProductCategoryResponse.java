package org.george.ecommerce.response;

import lombok.Getter;
import lombok.Setter;
import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.springframework.beans.BeanUtils;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ProductCategoryResponse {

    private Long id;
    private String name;
    private ProductCategoryResponse parent;

    public static ProductCategoryResponse fromDomain(final ProductCategoriesModel productCategory) {
        ProductCategoryResponse result = new ProductCategoryResponse();
        BeanUtils.copyProperties(productCategory, result);
        return result;
    }

    public static Set<ProductCategoryResponse> fromDomain(final Set<ProductCategoriesModel> productCategoryList) {
        Set<ProductCategoryResponse> result = new HashSet<>();
        for (ProductCategoriesModel productCategory : productCategoryList) {
            result.add(fromDomain(productCategory));
        }
        return result;
    }
}
