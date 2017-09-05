package com.midland.web.service;

import com.github.pagehelper.Page;
import com.midland.web.model.user.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 'ms.x' on 2017/8/7.
 */
public interface DingJiangService {
	Page<User> getUserList(User user, String pageSize, Model model, HttpServletRequest request);
}
