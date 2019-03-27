package com.sl.fy.mapper;

import com.sl.fy.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void addOrder(Order order);

    List<Order> getOrderListOrByTransactionId(@Param("transactionId") String transactionId);
}
