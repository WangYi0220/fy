package com.sl.fy.service;

import com.sl.fy.pojo.QRCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface QRCodeService {
    List<QRCode> getQRCodeList(Map<String, String> param);

    void upQRCode(QRCode qrCode, MultipartFile file);

    void saveQRCode(QRCode qrCode, MultipartFile file);

    void expirationCheck();
}
