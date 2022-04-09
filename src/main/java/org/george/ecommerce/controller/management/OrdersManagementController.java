package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.service.OrdersServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/orders")
public class OrdersManagementController {
    final OrdersServiceImpl ordersService;

    @GetMapping
    public ResponseEntity<?> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok().body(ordersService.getAllOrders(pageable));
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(ordersService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrdersModel ordersModel) {
        return ResponseEntity.ok().body(ordersService.createOrder(ordersModel));
    }

    @PutMapping("/id/{orderId}")
    public ResponseEntity<?> updateOrderByOrderId(
            @PathVariable("orderId") Long orderId,
            @RequestBody OrdersModel ordersModel) {
        return ResponseEntity.ok().body(ordersService.updateOrderByOrderId(orderId, ordersModel));
    }

    @DeleteMapping("/id/{orderId}")
    public ResponseEntity<?> deleteOrderByOrderId(
            @PathVariable("orderId") Long orderId) {
        ordersService.deleteOrderById(orderId);
        return ResponseEntity.ok().body("Deleted");
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrderByOrderId(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(ordersService.cancelOrderById(orderId));
    }
}
