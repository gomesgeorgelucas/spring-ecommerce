package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.domain.model.ProductsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Long>, JpaSpecificationExecutor<ProductsModel> {
    Page<Set<ProductsModel>> findAllByProductCategoriesIn(Set<ProductCategoriesModel> productCategories, Pageable pageable);
    Page<Set<ProductsModel>> findAllByProductCategoriesInAndProductNameContaining(Set<ProductCategoriesModel> productCategories, String productName, Pageable pageable);
    Optional<ProductsModel> findProductByProductName(String name);
}
