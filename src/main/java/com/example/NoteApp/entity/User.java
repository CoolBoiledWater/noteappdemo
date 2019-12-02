package com.example.NoteApp.entity;

import com.example.NoteApp.util.DateUtil;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author LiuZepeng
 * @date 2019-11-26 10:49
 */
@Data
public class User {
    private long userId;
    private String userName;
    private String userPassword;
    private Integer status;
    private Integer userStatus;
    private Timestamp createTime ;
    private Timestamp updateTime;
}
