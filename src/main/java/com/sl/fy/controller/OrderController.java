package com.sl.fy.controller;

import com.sl.fy.pojo.Order;
import com.sl.fy.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "订单")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("查询所有订单或者根据微信交易号查询")
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public List<Order> getOrder(String transactionId){
        return orderService.getOrderListOrByTransactionId(transactionId);
    }
}
