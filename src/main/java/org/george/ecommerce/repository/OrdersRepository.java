package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {
}
