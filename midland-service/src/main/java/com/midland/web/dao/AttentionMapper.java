package com.midland.web.dao;

import com.midland.web.model.Attention;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AttentionMapper {

    Attention selectAttentionById(Integer attention);

    int deleteAttentionById(Integer attention);

    int updateAttentionById(Attention attention);

    int insertAttention(Attention attention);

    List<Attention> findAttentionList(Attention attention);

    List<Attention> findAttentionByList(@Param("mapList") List<Map> mapList);

}
