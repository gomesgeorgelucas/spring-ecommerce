package org.george.ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ec_product_categories")
public class ProductCategoriesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_category")
    Long productCategoryId;

    @Column(name = "name_product_category", nullable = false)
    String productCategoryName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_category_parent", insertable = false, updatable = false)
    @JsonIgnoreProperties("productCategoryParent")
    private ProductCategoriesModel productCategoryParent;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = ProductCategoriesModel.class)
    @JsonIgnoreProperties("categoryProducts")
    private Set<ProductsModel> categoryProducts;

    @OneToMany(mappedBy = "productCategoryParent")
    private Set<ProductCategoriesModel> productCategoryChildren;
}
