package org.george.ecommerce.domain.model.user;

import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.context.annotation.Lazy;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ec_role_authorities",
            joinColumns = @JoinColumn(name="id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_authority")
    )
    Set<AuthoritiesModel> roleAuthorities = Sets.newHashSet();

    @ManyToMany
    @JoinTable(name = "ec_user_roles",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    Set<UsersModel> roleUsers = Sets.newHashSet();
}
