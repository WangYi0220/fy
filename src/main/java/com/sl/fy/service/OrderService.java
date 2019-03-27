package com.sl.fy.service;

import com.sl.fy.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    List<Order> getOrderListOrByTransactionId(@Param("transactionId") String transactionId);
}
