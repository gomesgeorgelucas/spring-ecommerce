package org.george.ecommerce.domain.dto.user;

import lombok.*;
import org.george.ecommerce.domain.enums.UserRoleEnum;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name is mandatory")
    String userName;
    @NotBlank(message = "Surname is mandatory")
    String userSurname;
    @NotBlank(message = "Login is mandatory")
    String userLogin;
    @NotBlank(message = "Password is mandatory")
    String userPassword;
    UserRoleEnum userRole;
}
