package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.OrdersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrdersService {
    Page<OrdersModel> getAllOrders(Pageable pageable);

    OrdersModel getOrderById(Long orderId);

    OrdersModel createProduct(OrdersModel ordersModel);

    OrdersModel updateOrder(Long orderId, OrdersModel productsModel);

    void deleteOrderById(Long orderId);
}
