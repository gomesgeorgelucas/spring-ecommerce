package org.george.ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "ec_user_orders",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_order")
    )
    @JsonIgnore
    Set<OrdersModel> userOrders;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "ec_user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role",
            nullable = false)
    )
    RolesModel userRole;

}
