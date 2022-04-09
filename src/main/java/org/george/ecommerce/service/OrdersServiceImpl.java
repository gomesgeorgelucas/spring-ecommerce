package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.repository.OrdersRepository;
import org.george.ecommerce.repository.ProductsRepository;
import org.george.ecommerce.repository.UsersRepository;
import org.george.ecommerce.service.interfaces.IOrdersService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.george.ecommerce.domain.enums.OrderStatusEnum.CREATED;

@AllArgsConstructor
@Service
public class OrdersServiceImpl implements IOrdersService {
    final OrdersRepository ordersRepository;
    final ProductsRepository productsRepository;
    final UsersRepository usersRepository;

    final Long EMPTY_STOCK = 0L;

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
    @Transactional
    public OrdersModel createProduct(OrdersModel ordersModel) {
        if (usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel orderCreator = usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).get();
        ArrayList<ProductsModel> shoppingCart = new ArrayList<>();
        for (ProductsModel product : ordersModel.getOrderedProducts()) {
            if (productsRepository.findProductsByProductName(product.getProductName()).isEmpty()) {
                throw new NotFoundException("Product not found");
            }

            ProductsModel storedProduct = productsRepository.findProductsByProductName(product.getProductName()).get();
            ProductsModel soldProduct = ProductsModel.builder().build();
            modelMapper.map(storedProduct, soldProduct);
            soldProduct.setProductQuantity(product.getProductQuantity());
            shoppingCart.add(soldProduct);
        }

        OrdersModel newOrder = OrdersModel.builder()
                .orderStatus(CREATED)
                .orderedProducts(shoppingCart)
                .orderUser(orderCreator)
                .build();

        OrdersModel orderCreated = ordersRepository.saveAndFlush(newOrder);

        for (ProductsModel product : shoppingCart) {
            ProductsModel productToUpdate = productsRepository.findById(product.getProductId()).get();
            Long remainingProducts = productToUpdate.getProductQuantity() - product.getProductQuantity();
            if (remainingProducts > 0) {
                productToUpdate.setProductQuantity(remainingProducts);
            } else {
                productToUpdate.setProductQuantity(EMPTY_STOCK);
            }
            productsRepository.save(productToUpdate);
        }

        return orderCreated;
    }

    @Override
    public OrdersModel updateOrder(Long orderId, OrdersModel ordersModel) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        OrdersModel storedOrder = ordersRepository.findById(orderId).get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(ordersModel, storedOrder);
        return ordersRepository.save(storedOrder);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        /**
         * If order status CREATED - Restore items to inventory.
         */
        OrdersModel orderToDelete = ordersRepository.findById(orderId).get();
        if (orderToDelete.getOrderStatus() == CREATED) {
            for (ProductsModel product :
                    orderToDelete.getOrderedProducts()) {
                ProductsModel productToRestore = productsRepository.findById(product.getProductId()).get();
                productToRestore.setProductQuantity(productToRestore.getProductQuantity() + product.getProductQuantity());
                productsRepository.save(productToRestore);
            }
        }


        ordersRepository.deleteById(orderId);
    }
}
