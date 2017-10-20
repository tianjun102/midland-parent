package com.midland.web.dao;

import com.midland.web.model.Popular;
import com.midland.web.model.QrCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QrCodeMapper {

	QrCode selectQrCodeById(Integer qrCode);

	int deleteQrCodeById(Integer qrCode);

	int updateQrCodeById(QrCode qrCode);

	int insertQrCode(QrCode qrCode);

	List<QrCode> findQrCodeList(QrCode qrCode);

	int batchUpdate(@Param("qrCodeList") List<QrCode> qrCodeList);

}
