package com.example.finaltest.service.impl;

import com.example.finaltest.dao.OrderDao;
import com.example.finaltest.dto.OrderDto;
import com.example.finaltest.dto.OrderResponseDto;
import com.example.finaltest.entity.Order;
import com.example.finaltest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderResponseDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order saveOrder = orderDao.insertOrder(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setId(saveOrder.getId());
        orderResponseDto.setProductId(saveOrder.getProductId());
        orderResponseDto.setProductName(saveOrder.getProductName());
        orderResponseDto.setUserId(saveOrder.getUserId());
        orderResponseDto.setUserName(saveOrder.getUserName());
        orderResponseDto.setPrice(saveOrder.getPrice());
        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> getAllOrder() {
        List<Order> orders = orderDao.selectAllBy();
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public List<OrderResponseDto> getAllOrderListByUserId(String userId) {
        List<Order> orders = orderDao.selectAllByUserId(userId);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public List<OrderResponseDto> getAllOrderListByProductId(Long productId) {
        List<Order> orders = orderDao.selectAllByProductId(productId);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public OrderResponseDto getOrderById(long Id) {
        Order order = orderDao.selectOrder(Id);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setProductId(order.getProductId());
        orderResponseDto.setProductName(order.getProductName());
        orderResponseDto.setUserId(order.getUserId());
        orderResponseDto.setUserName(order.getUserName());
        orderResponseDto.setPrice(order.getPrice());
        return orderResponseDto;
    }
}
