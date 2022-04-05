package org.george.ecommerce.domain.model;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity (name = "ec_products")
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    Long id;

    @Column(name = "name_product", nullable = false)
    String productName;

    @Column(name = "description_product")
    String description;
    @Column(name = "unit_price_product", nullable = false)
    BigDecimal unitPrice;
    @Column(name = "quantity_product")
    Long quantity;

    @ManyToMany
    @JoinTable(
            name = "ec_product_orders",
            joinColumns = @JoinColumn(name="id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_order")
    )
    Collection<OrdersModel> productsOrdered;

    @ManyToMany
    @JoinTable(name = "ec_product_categories_aux", joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_product_category"))
    Set<ProductCategoriesModel> productCategories;

    @ManyToOne
    @Lazy
    @JoinColumn(name = "id_product_creator_user", referencedColumnName = "id_user",nullable = false, updatable = false)
    private UsersModel productCreator;
}
