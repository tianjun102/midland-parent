package com.midland.web.dao;

import com.midland.web.model.Attention;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttentionMapper {

	Attention selectAttentionById(Integer attention);

	int deleteAttentionById(Integer attention);

	int updateAttentionById(Attention attention);

	int insertAttention(Attention attention);

	List<Attention> findAttentionList(Attention attention);

}
