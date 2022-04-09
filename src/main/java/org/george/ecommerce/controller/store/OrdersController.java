package org.george.ecommerce.controller.store;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.OrdersModel;
import org.george.ecommerce.service.OrdersServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/store/orders")
public class OrdersController {
    final OrdersServiceImpl ordersService;

    @GetMapping("/login/{userLogin}")
    public ResponseEntity<?> getAllOrdersByOrdersUserLogin(@PathVariable("userLogin") String userLogin,
                                          @PageableDefault(size = 5)
                                          @SortDefault.SortDefaults({
                                                  @SortDefault(sort = "orderTime", direction = Sort.Direction.DESC),
                                                  @SortDefault(sort = "orderStatus", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return ResponseEntity.ok().body(ordersService.getAllOrdersByOrdersUserLogin(userLogin, pageable));
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
    public ResponseEntity<?> cancelOrderByOrderId(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(ordersService.cancelOrderById(orderId));
    }
}
