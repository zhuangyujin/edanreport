package com.edan.report.service;

import com.edan.report.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edan.report.tool.R;

/**
 *
 */
public interface UserService extends IService<User> {

    R register(User user);

    R delete(User user);

    R update(User user);

    R read(User user);
}
