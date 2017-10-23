package com.midland.web.dao;

import com.midland.web.model.Discount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscountMapper {

	Discount selectDiscountById(Integer discount);

	int deleteDiscountById(Integer discount);

	int updateDiscountById(Discount discount);

	int insertDiscount(Discount discount);

	List<Discount> findDiscountList(Discount discount);

	int batchUpdate(@Param("discountList") List<Discount> discountList);

}
