package com.example.NoteApp.controller;

import com.example.NoteApp.entity.JsonResult;
import com.example.NoteApp.entity.User;
import com.example.NoteApp.service.UserService;
import com.example.NoteApp.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuZepeng
 * @date 2019-11-26 10:59
 */
@RestController
public class LoginController {

    @Autowired
    private  RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");

        return mv;
    }

    @PostMapping("/user/dologin")
    public JsonResult doLogin(@Param("user")User user, HttpServletRequest request){
        if("".equals(user.getUserName()) || "".equals(user.getUserPassword())){
            return JsonResult.error("密码或者账户为空");

        }
        User currentUser = userService.getUserByUserName(user);
        if(currentUser==null){
            return JsonResult.success("登录失败");
        }
        redisUtil.set("user",currentUser,60*60);
        return JsonResult.success("登录成功");

       /* if (user.getName().equals("zipliu") && user.getPassword().equals("123")){
            request.getSession().setAttribute("user",user);
            return JsonResult.success();
        }*/

    }
}
