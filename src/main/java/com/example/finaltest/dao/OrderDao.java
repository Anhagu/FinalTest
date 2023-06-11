package com.example.finaltest.dao;

import com.example.finaltest.entity.Order;

import java.util.List;

public interface OrderDao {
    Order insertOrder(Order order);
    Order selectOrder(long Id);
    List<Order> selectAllBy();
    List<Order> selectAllByUserId(String userId);
    List<Order> selectAllByProductId(Long productId);
}
