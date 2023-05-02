package com.edan.report.controller;

import com.edan.report.annotation.Check;
import com.edan.report.pojo.User;
import com.edan.report.service.UserService;
import com.edan.report.tool.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    //注册账号，增
    @PostMapping("/register")
    public R register(@RequestBody User user)
    {
        //user服务的注册
        return userService.register(user);
    }
    //删除账号，删
    @DeleteMapping("/delete")
    @Check
    public R delete(@RequestBody User user)
    {
        return userService.delete(user);
    }
    //修改账号,修
    @PutMapping("/update")
    @Check
    public R update(@RequestBody User user)
    {
        return userService.update(user);
    }
    //查找账号，查
    @GetMapping("/read")
    @Check
    public R read(User user)
    {
        return userService.read(user);
    }
}
