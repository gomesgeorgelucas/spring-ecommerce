package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.OrdersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrdersService {
    Page<OrdersModel> getAllOrders(Pageable pageable);

    Page<OrdersModel> getAllOrdersByOrdersUserLogin(String userLogin, Pageable pageable);

    OrdersModel getOrderById(Long orderId);

    OrdersModel createOrder(OrdersModel ordersModel);

    OrdersModel updateOrderByOrderId(Long orderId, OrdersModel productsModel);

    void deleteOrderById(Long orderId);

    OrdersModel cancelOrderById(Long orderId);


}
