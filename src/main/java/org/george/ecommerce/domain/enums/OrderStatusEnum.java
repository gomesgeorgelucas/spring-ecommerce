package org.george.ecommerce.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    CREATED ("Order Created"),
    SHIPPED ("Order Shipped"),
    COMPLETED ("Order completed"),
    CANCELLED ("Order canceled");

    final String status;
}
