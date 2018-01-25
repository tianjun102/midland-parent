package com.midland.web.service.impl;

import com.midland.web.commons.core.util.ApplicationUtils;
import com.midland.web.commons.core.util.DateUtils;
import com.midland.web.commons.exception.ServiceException;
import com.midland.web.dao.WebUserMapper;
import com.midland.web.model.WebUser;
import com.midland.web.service.WebUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yek
 * @Title: UserServiceImpl.java
 * @Package com.bluemobi.midland.web.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年3月22日 上午8:55:26
 */
@Service
public class WebUserServiceImpl implements WebUserService {

    private static Logger logger = LoggerFactory.getLogger(WebUserServiceImpl.class);

    @Resource
    private WebUserMapper webUserMapper;

    @Transactional(readOnly = true)
    @Override
    public int authentication(String phone) throws ServiceException {
        return webUserMapper.authentication(phone);
    }

    @Transactional(readOnly = true)
    @Override
    public WebUser getUserByUsername(String username) throws ServiceException {
        List<WebUser> list = webUserMapper.selectByUsername(username);
        if (null != list && list.size() == 1)
            return list.get(0);
        else {
            String msg = "系统根据用户名查询用户信息时出现多个用户名一致的用户。";
            ServiceException e = new ServiceException(msg);
            logger.error(msg, e);
            throw e;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public WebUser getUserByUserId(String userId) {

        return webUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int editPwdById(String newPwd, String id) throws ServiceException {

        return webUserMapper.updatePwdById(newPwd, id);
    }

    @Transactional
    @Override
    public int editUserById(WebUser user) throws ServiceException {

        return webUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addUser(Map<String, String> map) {
        WebUser user = new WebUser();
        user.setUsername(map.get("phone"));
        user.setUserCnName(map.get("phone"));
        user.setPassword(ApplicationUtils.sha256Hex(map.get("password")));
        user.setUserType(2);//前端用户
        user.setState(1);//生效
        user.setPhone(map.get("phone"));
        user.setCreateTime(DateUtils.nowDate());
        return webUserMapper.insertSelective(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WebUser> findByCreateByPage(Map<String, String> map) {
        WebUser user = new WebUser();
        user.setCreateBy(map.get("createBy"));
        user.setUserCnName(map.get("username"));
        user.setPhone(map.get("phone"));
        return webUserMapper.queryUserList(user);
    }

    @Transactional(readOnly = true)
    @Override
    public WebUser findParentUserByChild(String username) {
        return webUserMapper.selectParentUserByChild(username);
    }

    @Override
    public int updateUser(WebUser user) {
        return webUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public WebUser findtUserByEntity(WebUser user) {
        return webUserMapper.selectUserByEntity(user);
    }

    //自增userName
    public String getUserCnName(String pUserName, String createUid) {
        String temp = "";
        WebUser user = webUserMapper.queryUserByCreateUid(createUid);  // 查询数据最后添加的用户的userName ,自增
        if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
            String userName = user.getUsername();
            int num = Integer.valueOf(userName.replace(pUserName + "-", ""));
            temp = pUserName + "-" + (num + 1);
        } else {
            temp = pUserName + "-" + 1;
        }
        return temp;
    }

    @Override
    public int modifyUser(WebUser user) {
        // TODO Auto-generated method stub
        return webUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public WebUser queryUser(String uid) {
        // TODO Auto-generated method stub
        return webUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public String addWebUser(WebUser user) {
        return webUserMapper.addWebUser(user);
    }


}
