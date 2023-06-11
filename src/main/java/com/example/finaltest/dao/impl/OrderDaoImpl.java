package com.example.finaltest.dao.impl;

import com.example.finaltest.dao.OrderDao;
import com.example.finaltest.entity.Order;
import com.example.finaltest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderDaoImpl(OrderRepository orderedRepository) {
        this.orderRepository = orderedRepository;
    }

    @Override
    public Order insertOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order selectOrder(long Id) {
        Order selectOrder = orderRepository.getReferenceById(Id);
        return selectOrder;
    }

    @Override
    public List<Order> selectAllBy() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> selectAllByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> selectAllByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

}
