package com.example.finaltest.service;

import com.example.finaltest.dto.OrderDto;
import com.example.finaltest.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderDto orderDto);
    List<OrderResponseDto> getAllOrder();
    List<OrderResponseDto> getAllOrderListByUserId(String userId);
    List<OrderResponseDto> getAllOrderListByProductId(Long productId);
    OrderResponseDto getOrderById(long Id);
}
