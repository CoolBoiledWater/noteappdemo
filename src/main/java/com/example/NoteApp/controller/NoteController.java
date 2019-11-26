package com.example.NoteApp.controller;

import com.example.NoteApp.entity.JsonResult;
import com.example.NoteApp.entity.NoteEntity;
import com.example.NoteApp.service.NoteService;
import com.example.NoteApp.util.DateUtil;
import com.example.NoteApp.util.StrUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-11-25 10:14
 */
@RestController
@RequestMapping("/demo")
public class NoteController {
    @Autowired
    private NoteService service;

    /**
     * mvc 风格
     *
     * @param :
     * @return org.springframework.web.servlet.ModelAndView
     * @author LiuZepeng
     * @date 2019/11/25 16:13
     */
    @RequestMapping("/index.html")
    public ModelAndView index(){
        ModelAndView mv= new ModelAndView("index");
        List<NoteEntity> list = service.findAll();
        mv.addObject("notelist",list);

        return mv;
//        List<NoteEntity> list = service.findAll();
//        return JsonResult.success("",list);
    }

    @GetMapping("/insert.html")
    public ModelAndView insert(HttpServletRequest request){
        ModelAndView mv= new ModelAndView("detail");
        Long id = StrUtil.toLong(request.getParameter("id"), 0L);
        //mv.addObject("note",new NoteEntity());
        if (id!=0){
            mv.addObject("note",service.getOne(id));
        }
        return mv;
    }

    @PostMapping("/doinsert")
    public JsonResult doInsert(HttpServletRequest request){
        String title = StrUtil.toStr(request.getParameter("title"), "");
        String content = StrUtil.toStr(request.getParameter("content"), "");
        int insertNote = service.insertNote(title, content, DateUtil.dateToLong(), DateUtil.dateToLong());
        if (insertNote==0){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @PostMapping("/doupdate")
    public JsonResult doUpdate(@Param("note")NoteEntity noteEntity){
        int doUpdate = service.doUpdate(noteEntity);
        if (doUpdate==0){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * rest风格API
     * @param request
     * @return
     */
    @PostMapping("/delete.html")
    public JsonResult delnote(HttpServletRequest request){
        long id = StrUtil.toLong(request.getParameter("id"),0L);
        int deleteNote = service.deleteNote(id);
        if (deleteNote==0){
            JsonResult.error();
        }
        return JsonResult.success();
    }

}
