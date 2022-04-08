package org.george.ecommerce.domain.model;

import com.google.common.collect.Sets;
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

    @ManyToMany
    @JoinTable(
            name = "ec_category_products",
            joinColumns = @JoinColumn(name="id_product_category"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    Set<ProductsModel> categoryProducts = Sets.newHashSet();


}
