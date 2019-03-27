package com.sl.fy.service;

import com.sl.fy.mapper.ModelMapper;
import com.sl.fy.mapper.OrderMapper;
import com.sl.fy.pojo.Order;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加订单
     * @param order
     */
    @Override
    public void addOrder(Order order) {
        String openID = modelMapper.getModelAdminOpenID(2);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openID)
                .templateId("85sJEIyRbBEJJtE3qAs2O0S9eDFF6MaR9bGjD8tGsw4")
                .url("xxxx")
                .build();
        templateMessage.addData(new WxMpTemplateData("content", "有新学员报名！"));
        orderMapper.addOrder(order);
    }

    /**
     * 查询所有订单或者根据微信交易号查询
     * @param transactionId 微信交易号
     * @return
     */
    @Override
    public List<Order> getOrderListOrByTransactionId(String transactionId) {
        return orderMapper.getOrderListOrByTransactionId(transactionId);
    }
}
