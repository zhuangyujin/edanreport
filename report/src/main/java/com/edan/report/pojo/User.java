package com.edan.report.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userpassword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}