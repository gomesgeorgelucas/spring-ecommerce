package org.george.ecommerce.domain.model.orders;

import lombok.*;
import org.george.ecommerce.domain.model.product.ProductsModel;
import org.george.ecommerce.domain.model.user.UsersModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
    String orderStatus;

    @ManyToMany
    @JoinTable(
            name = "ec_orders",
            joinColumns = @JoinColumn(name="id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    Collection<ProductsModel> orderedProducts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ec_user_orders",
            joinColumns = @JoinColumn(name="id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    Collection<UsersModel> orderUsers = new ArrayList<>();
}
