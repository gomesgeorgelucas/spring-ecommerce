package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.george.ecommerce.domain.enums.OrderStatusEnum;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.repository.OrdersRepository;
import org.george.ecommerce.repository.ProductsRepository;
import org.george.ecommerce.repository.UsersRepository;
import org.george.ecommerce.service.interfaces.IOrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;

import static org.george.ecommerce.domain.enums.OrderStatusEnum.CREATED;

@AllArgsConstructor
@Service
public class OrdersServiceImpl implements IOrdersService {
    final OrdersRepository ordersRepository;
    final ProductsRepository productsRepository;
    final UsersRepository usersRepository;

    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<OrdersModel> getAllOrders(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    @Override
    public OrdersModel getOrderById(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        OrdersModel ordersModel = ordersRepository.findById(orderId).get();
        return ordersModel;
    }

    @Override
    public OrdersModel createProduct(OrdersModel ordersModel) {
        if (usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel orderCreator = usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).get();
        ArrayList<ProductsModel> shoppingCart = new ArrayList<>();
        for (ProductsModel product :
                ordersModel.getOrderedProducts()) {
            if (productsRepository.findProductsByProductName(product.getProductName()).isEmpty()) {
                throw new NotFoundException("Product not found");
            }

            shoppingCart.add(productsRepository.findProductsByProductName(product.getProductName()).get());
        }

        OrdersModel newOrder = OrdersModel.builder()
                .orderStatus(CREATED)
                .orderedProducts(shoppingCart)
                .orderUser(orderCreator)
                .build();

        return ordersRepository.save(newOrder);
    }

    @Override
    public OrdersModel updateOrder(Long orderId, OrdersModel ordersModel) {
        return null;
    }

    @Override
    public void deleteOrderById(Long orderId) {

    }
}
