package org.george.ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.Sets;
import lombok.*;
import org.george.ecommerce.domain.enums.UserRoleEnum;
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
    @Column(name = "name_role", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    UserRoleEnum roleName;

    @OneToMany
    @JoinTable(name = "ec_user_roles",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    @JsonIgnore
    Set<UsersModel> roleUsers;
}
