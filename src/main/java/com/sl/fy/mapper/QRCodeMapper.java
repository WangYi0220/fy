package com.sl.fy.mapper;

import com.sl.fy.pojo.QRCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QRCodeMapper {
    List<QRCode> getQRCodeList(@Param("param") Map<String, String> param);

    void upQRCode(QRCode qrCode);

    void saveQRCode(QRCode qrCode);

    List<QRCode> getQRCodeExpiresList();

}
