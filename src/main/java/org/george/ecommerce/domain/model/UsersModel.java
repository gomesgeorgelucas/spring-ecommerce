package org.george.ecommerce.domain.model;

import com.google.common.collect.Sets;
import lombok.*;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.domain.model.RolesModel;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ec_users")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long userId;

    @Column(name = "name_user", nullable = false)
    String userName;
    @Column(name = "surname_user", nullable = false)
    String userSurname;

    @Column(name = "login_user", unique = true, nullable = false)
    String userLogin;
    @Column(name = "passwd_user", nullable = false)
    String userPassword;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ec_user_orders",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_order")
    )
    Collection<OrdersModel> userOrders = Sets.newHashSet();

    @ManyToOne
    @JoinTable(name = "ec_user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    RolesModel userRole;

}
