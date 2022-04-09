package org.george.ecommerce.repository;

import org.george.ecommerce.domain.dto.ProductCategoryDTO;
import org.george.ecommerce.domain.model.ProductsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Long>, JpaSpecificationExecutor<ProductsModel> {
    @Query(
            value = "select p.name_product as name, p.unit_price_product AS price, pca.name_product_category AS category from ec_products as p " +
                    "    inner join ec_category_products as pc on p.id_product = pc.id_product " +
                    "    inner join ec_product_categories as pca on pca.id_product_category = pc.id_product_category ", nativeQuery = true
    )
    Page<ProductCategoryDTO> findByProductAndCategory(Pageable pageable);

    Optional<ProductsModel> findProductsByProductName(String name);
}
