package org.george.ecommerce.domain.model.user;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ec_authorities")
public class AuthoritiesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authority")
    Long authorityId;
    @Column(name = "name_authority")
    String authorityName;

    @ManyToMany
    @JoinTable(
            name = "ec_role_authorities",
            joinColumns = @JoinColumn(name="id_authority"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    Set<RolesModel> authorizedRoles = Sets.newHashSet();


}
