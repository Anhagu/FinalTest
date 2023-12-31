package com.example.finaltest.dto;

import com.example.finaltest.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private long id;
    private Long productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;
    private Order order;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.productName = order.getProductName();
        this.userId = order.getUserId();
        this.userName = order.getUserName();
        this.price = order.getPrice();
    }
    public OrderResponseDto(long id, Long productId, String productName, String userId, String userName, int price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.userId = userId;
        this.userName = userName;
        this.price = price;
    }
}
