package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.user.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
}
