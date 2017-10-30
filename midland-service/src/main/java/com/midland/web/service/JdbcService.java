package com.midland.web.service;

import com.midland.web.dao.SqlMapperDao;
import com.midland.web.model.temp.ListDescOtherParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/15.
 */
@Service
public class JdbcService {
    private final Logger log = LoggerFactory.getLogger(JdbcService.class);

    @Autowired
    private SqlMapperDao sqlMapperDao;

    public Map querySql(String sql) {

        return sqlMapperDao.querySql(sql);
    }

    public void doSql(String sql) {
        //System.out.println(sql);
        sqlMapperDao.doSql(sql);
    }

    /**
     * 上移
     *
     * @param primaryKeyName
     * @param id
     * @param orderByColumn
     * @param tableName
     * @param orderByParam
     * @return
     */
    public Map shiftUp(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam, ListDescOtherParam obj) {
        StringBuffer sb = new StringBuffer("SELECT ").append(primaryKeyName + ",").append(orderByColumn)
                .append(" from ").append(tableName).append(" where ").append("is_delete = 0 and ");
        if (StringUtils.isNotEmpty(obj.getCityId())) {
            sb.append("city_id=").append(obj.getCityId()).append(" and ");
        }
        if (StringUtils.isNotEmpty(obj.getType())) {
            sb.append(obj.getType()).append(" and ");
        }
        if (obj.getSource()!=null) {
            sb.append("source=").append(obj.getSource()).append(" and ");
        }
        sb.append(orderByColumn)
                .append("<").append(orderByParam).append(" order by ")
                .append(orderByColumn).append(" desc limit 1");
        Map map = querySql(sb.toString());
        return map;
    }

    /**
     * 下移
     *
     * @param primaryKeyName
     * @param id
     * @param orderByColumn
     * @param tableName
     * @param orderByParam
     * @return
     */
    public Map shiftDown(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam,ListDescOtherParam obj) {
        StringBuffer sb = new StringBuffer("SELECT ").append(primaryKeyName + ",").append(orderByColumn)
                .append(" from ").append(tableName).append(" where ").append("is_delete = 0 and ");
        if (StringUtils.isNotEmpty(obj.getCityId())) {
            sb.append("city_id=").append(obj.getCityId()).append(" and ");
        }
        if (StringUtils.isNotEmpty(obj.getType())) {
            //artice_type=0
            sb.append(obj.getType()).append(" and ");
        }
        if (obj.getSource()!=null) {
            sb.append("source=").append(obj.getSource()).append(" and ");
        }
                sb.append(orderByColumn)
                .append(">").append(orderByParam).append(" order by ")
                .append(orderByColumn).append(" asc limit 1");
        return querySql(sb.toString());

    }

    /**
     * 置顶
     *
     * @param primaryKeyName
     * @param id
     * @param orderByColumn
     * @param tableName
     * @return
     */
    public Map stickly(String primaryKeyName, String id, String orderByColumn, String tableName) {
        StringBuffer sb = new StringBuffer("SELECT ").append(primaryKeyName + ",").append(orderByColumn)
                .append("-1 as order_by from ").append(tableName).append(" order by ")
                .append(orderByColumn).append(" asc limit 1");
        return querySql(sb.toString());
    }

    public void updateSql(String whereColumn, String whereParam, String orderByColumn, String tableName, String orderByParam) {
        StringBuffer sb = new StringBuffer("update ").append(tableName).append(" set ")
                .append(orderByColumn).append("=").append(orderByParam).append(" where ")
                .append(whereColumn).append("=").append(whereParam);
        doSql(sb.toString());
    }

    /**
     * @param primaryKeyName 要排序表的主键名
     * @param id             要排序表的主键值
     * @param orderByColumn  要排序的列名称
     * @param tableName      要排序的表名
     * @param orderByParam   要对数据上移或者下移或者置顶的那条数据的排序序号
     * @param sort           0置顶，1上移，2下移，这个是页面控制后台逻辑用的，不保存到数据库
     */
    @Transactional(rollbackFor = Exception.class)
    public void listDesc(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam, ListDescOtherParam obj,int sort) {
        if (sort == 0) {
            Map map = stickly(primaryKeyName, id, orderByColumn, tableName);
            String primaryKeyId = String.valueOf(map.get(primaryKeyName));
            String descNumResult = String.valueOf(map.get(orderByColumn));
            updateSql(primaryKeyName, primaryKeyId, orderByColumn, tableName, descNumResult);
        } else if (sort == 1) {
            Map map = shiftUp(primaryKeyName, id, orderByColumn, tableName, orderByParam,obj);
            doExchangeDescNum(primaryKeyName, id, orderByColumn, orderByParam, tableName, map);
        } else if (sort == 2) {
            Map map = shiftDown(primaryKeyName, id, orderByColumn, tableName, orderByParam,obj);
            doExchangeDescNum(primaryKeyName, id, orderByColumn, orderByParam, tableName, map);
        } else {
            throw new IllegalArgumentException("没有这个sort排序方法");
        }
    }

    private void doExchangeDescNum(String primaryKeyName, String id, String orderByColumn, String orderByParam, String tableName, Map map) {
        if (map == null) {
            log.warn("上移下移排序，第一条不能上移，最后一条不能下移");
            return;
        }
        String primaryKeyId = String.valueOf(map.get(primaryKeyName));
        //要交换的排序号

        String descNumResult = String.valueOf(map.get(orderByColumn));
        updateSql(primaryKeyName, primaryKeyId, orderByColumn, tableName, "-99999");
        updateSql(primaryKeyName, id, orderByColumn, tableName, descNumResult);
        updateSql(primaryKeyName, primaryKeyId, orderByColumn, tableName, orderByParam);


    }
 private void doStickly(String primaryKeyName, String id, String orderByColumn, String orderByParam, String tableName, Map map) {
        if (map == null) {
            log.warn("上移下移排序，第一条不能上移，最后一条不能下移");
            return;
        }
        String primaryKeyId = String.valueOf(map.get(primaryKeyName));
        //要交换的排序号

        String descNumResult = String.valueOf(map.get(orderByColumn));
        updateSql(primaryKeyName, primaryKeyId, orderByColumn, tableName, "-99999");


    }





    //***********************************************************菜单排序**************************************************************************
    /**
     * @param primaryKeyName 要排序表的主键名
     * @param id             要排序表的主键值
     * @param orderByColumn  要排序的列名称
     * @param tableName      要排序的表名
     * @param orderByParam   要对数据上移或者下移或者置顶的那条数据的排序序号
     * @param sort           0置顶，1上移，2下移，这个是页面控制后台逻辑用的，不保存到数据库
     */
    @Transactional(rollbackFor = Exception.class)
    public void menuListDesc(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam, ListDescOtherParam obj,int sort) {
        if (sort == 0) {
            Map map = stickly(primaryKeyName, id, orderByColumn, tableName);
            doExchangeDescNum(primaryKeyName, id, orderByColumn, orderByParam, tableName, map);
        } else if (sort == 1) {
            Map map = menuShiftUp(primaryKeyName, id, orderByColumn, tableName, orderByParam,obj);
            doExchangeDescNum(primaryKeyName, id, orderByColumn, orderByParam, tableName, map);
        } else if (sort == 2) {
            Map map = menuShiftDown(primaryKeyName, id, orderByColumn, tableName, orderByParam,obj);
            doExchangeDescNum(primaryKeyName, id, orderByColumn, orderByParam, tableName, map);
        } else {
            throw new IllegalArgumentException("没有这个sort排序方法");
        }
    }

    /**
     * 上移
     *
     * @param primaryKeyName
     * @param id
     * @param orderByColumn
     * @param tableName
     * @param orderByParam
     * @return
     */
    public Map menuShiftUp(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam, ListDescOtherParam obj) {
        StringBuffer sb = new StringBuffer("SELECT ").append(primaryKeyName + ",").append(orderByColumn)
                .append(" from ").append(tableName).append(" where ").append("is_delete = 0 and ");
        if (StringUtils.isNotEmpty(obj.getCityId())) {
            sb.append("city_id=").append(obj.getCityId()).append(" and ");
        }
        if (StringUtils.isNotEmpty(obj.getType())) {
            //artice_type=0
            sb.append(obj.getType()).append(" and ");
        }
        if (obj.getSource()!=null) {
            sb.append("source=").append(obj.getSource()).append(" and ");
        }
        sb.append(orderByColumn)
                .append("<").append(orderByParam).append(" order by ").append("source,")
                .append(orderByColumn).append(" desc limit 1");
        Map map = querySql(sb.toString());
        return map;
    }

    /**
     * 下移
     *
     * @param primaryKeyName
     * @param id
     * @param orderByColumn
     * @param tableName
     * @param orderByParam
     * @return
     */
    public Map menuShiftDown(String primaryKeyName, String id, String orderByColumn, String tableName, String orderByParam,ListDescOtherParam obj) {
        StringBuffer sb = new StringBuffer("SELECT ").append(primaryKeyName + ",").append(orderByColumn)
                .append(" from ").append(tableName).append(" where ").append("is_delete = 0 and ");
        if (StringUtils.isNotEmpty(obj.getCityId())) {
            sb.append("city_id=").append(obj.getCityId()).append(" and ");
        }
        if (StringUtils.isNotEmpty(obj.getType())) {
            //artice_type=0
            sb.append(obj.getType()).append(" and ");
        }
        if (obj.getSource()!=null) {
            sb.append("source=").append(obj.getSource()).append(" and ");
        }
        sb.append(orderByColumn)
                .append(">").append(orderByParam).append(" order by ").append("source,")
                .append(orderByColumn).append(" asc limit 1");
        return querySql(sb.toString());

    }

}
