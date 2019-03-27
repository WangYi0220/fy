package com.sl.fy.service;

import com.sl.fy.mapper.TicketMapper;
import com.sl.fy.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    /**
     * 查询所有用户优惠券或根据用户编号查询
     * @param stuOpenID
     * @return
     */
    @Override
    public List<Ticket> getTickerOrList(String stuOpenID) {
        return ticketMapper.getTickerOrList(stuOpenID);
    }

    /**
     * 更新优惠券
     * @param ticket
     */
    @Override
    public void upTicket(Ticket ticket) {
        ticketMapper.upTicket(ticket, null);
    }
}
