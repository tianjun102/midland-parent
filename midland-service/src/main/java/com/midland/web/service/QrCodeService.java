package com.midland.web.service;

import com.midland.web.model.QrCode;

import java.util.List;

public interface QrCodeService {

    /**
     * 主键查询
     **/
    QrCode selectQrCodeById(Integer id);

    /**
     * 主键删除
     **/
    void deleteQrCodeById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateQrCodeById(QrCode qrCode) throws Exception;

    /**
     * 插入
     **/
    void insertQrCode(QrCode qrCode) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<QrCode> findQrCodeList(QrCode qrCode) throws Exception;

    void batchUpdate(List<QrCode> qrCodeList) throws Exception;

}
