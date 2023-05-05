package com.edan.report.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edan.report.pojo.User;
import com.edan.report.service.UserService;
import com.edan.report.mapper.UserMapper;
import com.edan.report.tool.R;
import com.edan.report.utils.TokenUtils;
import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public R register(User user) {
        //先查看有无注册
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.select(User::getUserName).eq(User::getUserName,user.getUserName());
        Integer integer = userMapper.selectCount(lambdaQueryWrapper);
        if (integer>0)
        {
            return  R.error("已注册");
        }
        else
        {


            int insert = userMapper.insert(user);
            //把userMapper.insert抽象化一个对象，然后对象.run（user），这样子我就可以抽象化增删查改，放入动作对象，和user实体对象
            if (insert==1) {
                //返回token
                HashMap<String,String> map = new HashMap<String,String>();
                String token = TokenUtils.getToken(user.getUserId(), user.getUserpassword());
                map.put("token",token);
                log.info("token{}",token);
                return R.success(map);
            }

            else
                return R.error("未知错误");
        }
    }


    @Override
    public R delete(User user) {
        String userFind = user.getUserId();
        if (userFind==null)
            return R.error("无该用户");
        else
        {
            int delete = userMapper.deleteById(user);
            if (delete==1)
                return R.success("删除成功");
            else
                return R.error("删除失败");
        }
    }

    @Override
    public R update(User user) {
        String userFind = user.getUserId();
        if (userFind==null)
            return R.error("无该用户");
        else
        {
            int update = userMapper.updateById(user);
            if (update==1)
                return R.success("更新成功");
            else
                return R.error("更新失败");
        }
    }

    @Override
    public R read(User user) {
        User read = userMapper.selectById(user);
        if (read==null)
            return R.error("无该用户");
        else
            return R.success(read);
    }
}




