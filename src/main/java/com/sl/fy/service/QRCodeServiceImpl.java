package com.sl.fy.service;

import com.sl.fy.common.utils.DateUtils;
import com.sl.fy.common.utils.FileUtil;
import com.sl.fy.mapper.ModelMapper;
import com.sl.fy.mapper.QRCodeMapper;
import com.sl.fy.pojo.QRCode;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QRCodeServiceImpl implements QRCodeService {
    private static final String PATH_PREFIX = "qrCode/";

    @Autowired
    private QRCodeMapper qrCodeMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WxMpService wxMpService;

    /**
     * 查询所有二维码
     *
     * @param param（可分类 0会员群 1导师会员群 2导师面试群）
     * @return
     */
    @Override
    public List<QRCode> getQRCodeList(Map<String, String> param) {
        return qrCodeMapper.getQRCodeList(param);
    }

    /**
     * 管理二维码
     *
     * @param qrCode
     */
    @Override
    public void upQRCode(QRCode qrCode, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String qrCodePath = FileUtil.fileUP(file, PATH_PREFIX);
            System.out.println(qrCode.getQrCode());
            FileUtil.fileDel(qrCode.getQrCode());
            qrCode.setQrCode(qrCodePath);
            qrCode.setExpires(DateUtils.getFutureDate(6));//获取六天后的时间
        }
        qrCodeMapper.upQRCode(qrCode);
    }

    /**
     * 添加二维码
     *
     * @param qrCode
     */
    @Override
    public void saveQRCode(QRCode qrCode, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String qrCodePath = FileUtil.fileUP(file, PATH_PREFIX);
            qrCode.setQrCode(qrCodePath);
            qrCode.setExpires(DateUtils.getFutureDate(6));//获取六天后的时间
        }
        qrCodeMapper.saveQRCode(qrCode);
    }

    /**
     * 检查二维码是否将要过期，过期则推送消息至管理员
     */
    @Scheduled(cron = "0 0 5 * * ?")//每天早上五点开始检查
    @Override
    public void expirationCheck() {
        System.out.println("11111111111");
        long currentTime = new Date().getTime();
        List<QRCode> qrCodeList = qrCodeMapper.getQRCodeExpiresList();
        String openID = modelMapper.getModelAdminOpenID(1);//获取管理员openID
        qrCodeList.forEach(item -> {
            if (item.getExpires().getTime() < currentTime) {
                System.out.println("二维码过期了。。。。。");
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(openID)
                        .templateId("whs5Ls8kkViy90Y8jeZVH0oBABmSFUN7yizdlzKZn34")
                        .url("http://www.cloudcom.org.cn/index2.html#/upQRCode?id=" + item.getId())
                        .build();
//                templateMessage.addWxMpTemplateData(new WxMpTemplateData("title", item.getTitle()));
                templateMessage.addData(new WxMpTemplateData("title", item.getTitle()));
                try {
                    wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
