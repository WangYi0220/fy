package com.sl.fy.service;

import com.sl.fy.common.utils.DateUtils;
import com.sl.fy.mapper.ChooseMapper;
import com.sl.fy.mapper.TicketMapper;
import com.sl.fy.pojo.Choose;
import com.sl.fy.pojo.Ticket;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChooseServiceImpl implements ChooseService {
    @Autowired
    private ChooseMapper chooseMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private WxMpService wxMpService;


    /**
     * 学员选择导师
     *
     * @param choose
     */
    @Override
    public String chooseTeacher(Choose choose) {
        List<Ticket> tickerOrList = ticketMapper.getTickerOrList(choose.getStuOpenID());
        Ticket ticket = tickerOrList.get(0);
        if (ticket.getSum() <= 0) {
            return "no";
        }
        chooseMapper.chooseTeacher(choose);
        if (ticket == null) ticket = new Ticket();
        ticket.setStuOpenID(choose.getStuOpenID());
        ticket.setSum(-1);//可用优惠券减1
        ticket.setUsingNub(1);//正在使用优惠券加1
        ticket.setUsedNub(0);//已使用过的优惠券数量不变
        ticketMapper.upTicket(ticket, null);
        return "ok";
    }

    /**
     * 导师选择学员
     * pass、面试、通过
     *
     * @param choose
     */
    @Override
    public void chooseStudent(Choose choose, List<String> stuOpenIDs) {
        Ticket ticket = new Ticket();
        if(choose.getFlag() == 1){//通知面试
            stuOpenIDs.forEach(item -> {
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(item)
                        .templateId("85sJEIyRbBEJJtE3qAs2O0S9eDFF6MaR9bGjD8tGsw4")
                        .url("xxxx")
                        .build();
//                templateMessage.addWxMpTemplateData(new WxMpTemplateData("content", "导师邀请您面试！"));
                templateMessage.addData(new WxMpTemplateData("content", "导师邀请您面试！"));
            });
        }
        if (choose.getFlag() == 2) {//当被选时
            choose.setOverTime(DateUtils.getFutureDate(60));//设置私塾到期时间(60天后)
            ticket.setStuOpenID(choose.getStuOpenID());
            ticket.setSum(0);//可用优惠券不变
            ticket.setUsingNub(-1);//使用中的优惠券减1
            ticket.setUsedNub(1);//已使用过的优惠券数量加1
            ticketMapper.upTicket(ticket, stuOpenIDs);
            stuOpenIDs.forEach(item -> {
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(item)
                        .templateId("85sJEIyRbBEJJtE3qAs2O0S9eDFF6MaR9bGjD8tGsw4")
                        .url("xxxx？")
                        .build();
//                templateMessage.addWxMpTemplateData(new WxMpTemplateData("content", "恭喜您，已被导师选中！请开始学习。"));
                templateMessage.addData(new WxMpTemplateData("content", "恭喜您，已被导师选中！请开始学习。"));
            });
        }
        if (choose.getFlag() == 3) {//当未选时
            ticket.setStuOpenID(choose.getStuOpenID());
            ticket.setSum(1);//可用优惠券加1
            ticket.setUsingNub(-1);//使用中的优惠券减1
            ticket.setUsedNub(0);//已使用过的优惠券数量不变
            ticketMapper.upTicket(ticket, stuOpenIDs);//退还赋羽券
            stuOpenIDs.forEach(item -> {
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(item)
                        .templateId("85sJEIyRbBEJJtE3qAs2O0S9eDFF6MaR9bGjD8tGsw4")
                        .url("xxxx")
                        .build();
//                templateMessage.addWxMpTemplateData(new WxMpTemplateData("content", "很遗憾，导师未选中您！请重新选中导师。"));
                templateMessage.addData(new WxMpTemplateData("content", "很遗憾，导师未选中您！请重新选中导师。"));
            });
        }
        chooseMapper.chooseStudent(choose, stuOpenIDs);
    }

    /**
     * 根据导师编号查询选择该导师的入选/待选的学生
     *
     * @param teaOpenID
     * @param flag
     * @return
     */
    @Override
    public List<Map<String, Object>> getStudentByTeaOpenID(String teaOpenID, int flag) {
        return chooseMapper.getStudentByTeaOpenID(teaOpenID, flag);
    }

    /**
     * 导师打分
     *
     * @param choose
     */
    @Override
    public void giveMark(Choose choose) {
        chooseMapper.giveMark(choose);
        System.out.println(choose.getId());
        System.out.println(choose.getStuOpenID());
        //微信推送给学员。。。。。。
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(choose.getStuOpenID())
                .templateId("Bn7CHCVB3V_0mqLLA8GQBn93thm8ngaWCx121hhKSls")
                .url("http://www.cloudcom.org.cn/getMark?id=" + choose.getId())
                .build();
//        templateMessage.addWxMpTemplateData(new WxMpTemplateData("star", "" + choose.getStar(), "#000000"));
//        templateMessage.addWxMpTemplateData(new WxMpTemplateData("remark", choose.getRemark().substring(0, 8) + "...", "#000000"));
        templateMessage.addData(new WxMpTemplateData("star", "" + choose.getStar(), "#000000"));
        templateMessage.addData(new WxMpTemplateData("remark", choose.getRemark().substring(0, 8) + "...", "#000000"));
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取导师评语
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getMark(int id) {
        return chooseMapper.getMark(id);
    }

    /**
     * 提醒导师打分
     *
     * @return
     */
    @Scheduled(cron = "0 30 5 * * ?")//每天早上五点半开始检查
    //@Scheduled(fixedDelay = 5000)测试
    public void checkTeacherGiveMark() {
        List<Map<String, Object>> chooses = chooseMapper.checkTeacherGiveMark();
        chooses.forEach(item -> {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser((String) item.get("teaOpenID"))
                    .templateId("eV2YIFeOIOnQexfsVIyUID2q7xm_D1PsvG5sNElYfcE")
                    .url("http://www.cloudcom.org.cn/getMark?id=" + item.get("id"))
                    .build();
            //templateMessage.addWxMpTemplateData("");
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取学员选择情况（导师编号，是否入选）
     *
     * @param stuOpenID
     * @return
     */
    @Override
    public Map<String, Object> getChooseByStuID(String stuOpenID) {
        return chooseMapper.getChooseByStuID(stuOpenID);
    }

    /**
     * 设置到期标识
     * 注：该方法定时执行时间一定要早于提醒导师打分定时执行时间（checkTeacherGiveMark）
     */
    @Scheduled(cron = "0 30 2 * * ?")//每天早上二点半开始检查
    //@Scheduled(fixedDelay = 5000)测试
    public void checkOverTime() {
        System.out.println("method:checkOverTime, 执行了");
        List<Integer> ids = chooseMapper.getOverTime();
        if (ids == null || ids.size() == 0) return;
        chooseMapper.setOverFlag(ids);
    }
}
