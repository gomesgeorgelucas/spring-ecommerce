package org.george.ecommerce.domain.model;

import lombok.*;
import org.george.ecommerce.domain.enums.OrderStatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ec_orders")
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    Long orderId;

    @CreationTimestamp
    @Column(name = "time_order")
    ZonedDateTime orderTime;
    @UpdateTimestamp
    @Column(name = "updated_order")
    ZonedDateTime orderUpdated;

    @Column(name = "status_order", nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatusEnum orderStatus;

    @ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "ec_ordered_products",
            joinColumns = @JoinColumn(name="id_order", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_product", nullable = false, updatable = false)
    )
    Collection<ProductsModel> orderedProducts;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinTable(
            name = "ec_user_orders",
            joinColumns = @JoinColumn(name="id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    UsersModel orderUser;
}
