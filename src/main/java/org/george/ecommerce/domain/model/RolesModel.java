package org.george.ecommerce.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ec_roles")
public class RolesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    Long roleId;
    @Column(name = "name_role")
    String roleName;

    @ManyToMany
    @JoinTable(
            name = "ec_role_authorities",
            joinColumns = @JoinColumn(name="id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_authority")
    )
    Set<AuthoritiesModel> roleAuthorities;

    @ManyToMany
    @JoinTable(name = "ec_user_roles",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    Set<UsersModel> roleUsers;
}
