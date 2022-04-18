package com.example.demo.service;

import com.example.demo.dao.OrderMapper;
import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public void insertOrder(Order order){
        orderMapper.insert(order.getSid(),order.getTimestamp(),order.getTel(),order.getCount(),order.getSum());
    }

}
