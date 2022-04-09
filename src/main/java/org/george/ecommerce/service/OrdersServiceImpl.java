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
import java.util.Collection;

import static org.george.ecommerce.domain.enums.OrderStatusEnum.*;

@AllArgsConstructor
@Service
public class OrdersServiceImpl implements IOrdersService {
    final OrdersRepository ordersRepository;
    final ProductsRepository productsRepository;
    final UsersRepository usersRepository;

    final Long EMPTY_STOCK = 0L;

    final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Page<OrdersModel> getAllOrders(Pageable pageable) {
        if (ordersRepository.findAll(pageable).isEmpty()) {
            throw new NotFoundException("No orders found");
        }

        Page<OrdersModel> pageReturned = ordersRepository.findAll(pageable);
        return pageReturned;
    }

    @Transactional
    @Override
    public OrdersModel getOrderById(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        OrdersModel ordersModel = ordersRepository.findById(orderId).get();
        Collection<ProductsModel> orderedProducts = ordersModel.getOrderedProducts();
        return ordersModel;
    }

    @Override
    @Transactional
    public OrdersModel createOrder(OrdersModel ordersModel) {
        //TODO - check if quantity is available
        if (usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }

        UsersModel orderCreator = usersRepository.findByUserLogin(ordersModel.getOrderUser().getUserLogin()).get();
        ArrayList<ProductsModel> shoppingCart = new ArrayList<>();
        for (ProductsModel product : ordersModel.getOrderedProducts()) {
            if (productsRepository.findProductByProductName(product.getProductName()).isEmpty()) {
                throw new NotFoundException("Product not found");
            }

            ProductsModel storedProduct = productsRepository.findProductByProductName(product.getProductName()).get();
            ProductsModel soldProduct = ProductsModel.builder().build();
            modelMapper.map(storedProduct, soldProduct);

            for (long productQuantity = 0; productQuantity < product.getProductQuantity(); productQuantity++) {
                soldProduct.setProductQuantity(1L);
                shoppingCart.add(soldProduct);
            }
        }

        OrdersModel newOrder = OrdersModel.builder()
                .orderStatus(CREATED)
                .orderedProducts(shoppingCart)
                .orderUser(orderCreator)
                .build();

        OrdersModel orderCreated = ordersRepository.save(newOrder);


        for (ProductsModel product : shoppingCart) {
            ProductsModel productToUpdate = productsRepository.findById(product.getProductId()).get();
            if (productToUpdate.getProductQuantity() > 0) {
                productToUpdate.setProductQuantity(productToUpdate.getProductQuantity()-1);
            } else {
                productToUpdate.setProductQuantity(EMPTY_STOCK);
            }

            if (productToUpdate.getProductsOrdered() == null) {
                productToUpdate.setProductsOrdered(new ArrayList<>());
            }

            productToUpdate.getProductsOrdered().add(orderCreated);
            productsRepository.save(productToUpdate);
        }

        return orderCreated;
    }

    @Override
    public OrdersModel updateOrderByOrderId(Long orderId, OrdersModel ordersModel) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        OrdersModel storedOrder = ordersRepository.findById(orderId).get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(ordersModel, storedOrder);
        return ordersRepository.save(storedOrder);
    }

    @Override
    public OrdersModel cancelOrderById(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        Collection<ProductsModel> orderedProducts = ordersRepository.findById(orderId).get().getOrderedProducts();

        OrdersModel orderToCancel = ordersRepository.findById(orderId).get();
        if (orderToCancel.getOrderStatus() != COMPLETED
                && orderToCancel.getOrderStatus() != CANCELLED) {
            orderToCancel.setOrderStatus(CANCELLED);
            restoreProductInventory(orderId);
        }

        return orderToCancel;
    }

    @Override
    @Transactional
    public void deleteOrderById(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        restoreProductInventory(orderId);

        OrdersModel ordersToDelete = ordersRepository.findById(orderId).get();
        ordersToDelete.setOrderedProducts(null);
        ordersToDelete.setOrderUser(null);
        OrdersModel saved = ordersRepository.save(ordersToDelete);
        //ordersRepository.deleteById(orderId);
    }

    /**
     * Tries to restore order's products back to the inventory.
     * @param orderId - Order identifier
     */
    @Transactional
    public void restoreProductInventory(Long orderId) {
        try {
            OrdersModel orderToRevert = ordersRepository.findById(orderId).get();
            if (orderToRevert.getOrderStatus() == CREATED) {
                for (ProductsModel product : orderToRevert.getOrderedProducts()) {
                    ProductsModel productToRestore = productsRepository.findById(product.getProductId()).get();
                    productToRestore.setProductQuantity(productToRestore.getProductQuantity()+1L);
                    productsRepository.save(productToRestore);
                }
            }
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
    }

    public Page<OrdersModel> getAllOrdersByOrdersUserLogin(String userLogin, Pageable pageable) {
        if (usersRepository.findByUserLogin(userLogin).isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return ordersRepository.findAllOrdersByOrderUserLogin(userLogin, pageable);

    }
}
