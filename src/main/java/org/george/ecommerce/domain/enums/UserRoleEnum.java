package org.george.ecommerce.domain.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static org.george.ecommerce.domain.enums.UserPermissions.*;

@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    USER (Sets.newHashSet(USER_READ, USER_WRITE, PRODUCT_READ, ORDER_READ, ORDER_WRITE)),
    ADMIN (Sets.newHashSet(USER_READ, USER_WRITE, PRODUCT_READ, PRODUCT_WRITE, ORDER_READ, ORDER_WRITE));

    final Set<UserPermissions> permissions;

//    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//
//        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
//        return permissions;
//    }

}
