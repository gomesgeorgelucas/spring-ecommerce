package org.george.ecommerce.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ec_clients")
public class ClientsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    Long clientId;

    @Column(name = "name_client")
    String clientName;

    @ManyToMany
    @JoinTable(
            name = "ec_client_orders",
            joinColumns = @JoinColumn(name="id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_order")
    )
    Collection<OrdersModel> clientOrders;

    @OneToOne (targetEntity = UsersModel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_user", referencedColumnName = "id_user")
    UsersModel clientUser;
}

