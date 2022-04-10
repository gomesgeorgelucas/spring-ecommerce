package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.service.OrdersServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/orders")
public class OrdersManagementController {
    final OrdersServiceImpl ordersService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok().body(ordersService.getAllOrders(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(
            @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(ordersService.getOrderById(orderId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid OrdersModel ordersModel) {
        return ResponseEntity.ok().body(ordersService.createOrder(ordersModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderByOrderId(
            @PathVariable("orderId") Long orderId,
            @RequestBody @Valid OrdersModel ordersModel) {
        return ResponseEntity.ok().body(ordersService.updateOrderByOrderId(orderId, ordersModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrderByOrderId(
            @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(ordersService.cancelOrderById(orderId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrderByOrderId(
            @PathVariable("orderId") Long orderId) {
        ordersService.deleteOrderById(orderId);
        return ResponseEntity.ok().body("Deleted");
    }


}
