package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategoriesModel, Long> {
    Optional<ProductCategoriesModel> findProductCategoryByProductCategoryName(String productName);
}
