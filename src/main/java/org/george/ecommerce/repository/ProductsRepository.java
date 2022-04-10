package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.views.ProductCategoriesModelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Long>, JpaSpecificationExecutor<ProductsModel> {
    @Query(
            value = "select p.name_product as name, p.unit_price_product AS price, pca.name_product_category AS category from ec_products as p " +
                    "    inner join ec_category_products as pc on p.id_product = pc.id_product " +
                    "    inner join ec_product_categories as pca on pca.id_product_category = pc.id_product_category ", nativeQuery = true
    )
    Page<ProductCategoriesModelView> findByProductAndCategory(Pageable pageable);
    Optional<ProductsModel> findProductByProductName(String name);

    @Query(value = "select distinct p.name_product, p.description_product, p.unit_price_product, p.quantity_product from ec_products as p " +
            "    inner join (select distinct cp.id_product, cp.id_product_category from ec_category_products as cp " +
            "    inner join (select distinct * from ec_product_categories as pc where pc.name_product_category = ?1) as pc on cp.id_product_category = pc.id_product_category) as filter1 " +
            "        on filter1.id_product =  p.id_product", nativeQuery = true)
    Page<ProductsModel> findAllByProductCategoriesContaining(@Param("categoryName") String categoryName, Pageable pageable);
}
