package com.example.NoteApp.controller;

import com.example.NoteApp.entity.JsonResult;
import com.example.NoteApp.entity.User;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login.html")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");

        return mv;
    }

    @PostMapping("/dologin")
    public JsonResult doLogin(@Param("user")User user, HttpServletRequest request){
        if (user.getName().equals("zipliu") && user.getPassword().equals("123")){
            request.getSession().setAttribute("user",user);
            return JsonResult.success();
        }
        return JsonResult.error();
    }
}
