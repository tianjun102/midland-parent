package com.midland.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/15.
 */
@Repository
public class SqlMapperDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map querySql(String sql) {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        Map map = null;
        if (null != mapList && mapList.size() > 0) {
            map = mapList.get(0);
        }
        return map;
    }

    public void doSql(String sql) {
        jdbcTemplate.execute(sql);
    }


}
