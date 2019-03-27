package com.sl.fy.service;

import com.sl.fy.pojo.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTickerOrList(String stuOpenID);

    void upTicket(Ticket ticket);
}
