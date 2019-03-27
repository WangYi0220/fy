package com.sl.fy.controller;

import com.sl.fy.pojo.Ticket;
import com.sl.fy.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "优惠券")
@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @ApiOperation(value = "查询所有用户优惠券或根据用户编号查询")
    @RequestMapping(value = "/getTickerOrList", method = RequestMethod.GET)
    public List<Ticket> getTickerOrList(String stuOpenID) {
        return ticketService.getTickerOrList(stuOpenID);
    }

    @ApiOperation(value = "更新优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuOpenID", value = "学员编号（优惠券数量没有变动记得传0）", paramType = "query"),
            @ApiImplicitParam(name = "sum", value = "可使用优惠券量 如增加一张直接传1 减少一张直接传 -1", paramType = "query"),
            @ApiImplicitParam(name = "usingNub", value = "使用中的优惠券 道理同上 这里+1 sum就-1", paramType = "query"),
            @ApiImplicitParam(name = "usedNub", value = "已经使用的优惠券 道理同上 这里+1 sum就-1", paramType = "query")
    })
    @RequestMapping(value = "/upTicket", method = RequestMethod.POST)
    public String upTicket(Ticket ticket){
        ticketService.upTicket(ticket);
        return "ok";
    }
}
