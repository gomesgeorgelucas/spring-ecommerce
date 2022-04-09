package org.george.ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    Long productId;

    @Column(name = "name_product", unique = true, nullable = false)
    String productName;
    @Column(name = "description_product")
    String productDescription;
    @Column(name = "unit_price_product", nullable = false)
    BigDecimal productUnitPrice;
    @Column(name = "quantity_product")
    Long productQuantity;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Lazy
    @JoinTable(
            name = "ec_product_orders",
            joinColumns = @JoinColumn(name="id_product", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_order", nullable = false, updatable = false)
    )
    @JsonIgnore
    Collection<OrdersModel> productsOrdered;

    @ManyToMany(fetch = FetchType.EAGER)
    @Lazy
    @JoinTable(
            name = "ec_category_products",
            joinColumns = @JoinColumn(name="id_product", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_product_category", nullable = false, updatable = false)
    )
    Set<ProductCategoriesModel> productCategories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id_product_creator_user",
            referencedColumnName = "id_user",
            nullable = false,
            updatable = false)
    UsersModel productCreator;
}
