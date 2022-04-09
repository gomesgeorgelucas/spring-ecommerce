package org.george.ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany
    @JoinTable(
            name = "ec_orders",
            joinColumns = @JoinColumn(name="id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    Collection<ProductsModel> orderedProducts;

    @ManyToOne
    @JoinTable(
            name = "ec_user_orders",
            joinColumns = @JoinColumn(name="id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @JsonIgnore
    UsersModel orderUser;
}
