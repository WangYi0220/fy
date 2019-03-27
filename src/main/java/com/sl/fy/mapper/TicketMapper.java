package com.sl.fy.mapper;

import com.sl.fy.pojo.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketMapper {
    List<Ticket> getTickerOrList(@Param("stuOpenID") String stuOpenID);

    void upTicket(@Param("ticket") Ticket ticket, @Param("stuOpenIDs") List<String> stuOpenIDs);
}
