package org.george.ecommerce.repository;

import org.george.ecommerce.domain.model.OrdersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {
    @Query(value = "select o from ec_orders o where o.orderUser.userLogin = :login")
    Page<OrdersModel> findAllOrdersByOrderUserLogin(@Param("login") String userLogin, Pageable pageable);
}
