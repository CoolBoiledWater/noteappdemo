package com.example.NoteApp.service.impl;

import com.example.NoteApp.entity.NoteEntity;
import com.example.NoteApp.mapper.NoteMapper;
import com.example.NoteApp.service.NoteService;
import com.example.NoteApp.util.DateUtil;
import com.example.NoteApp.util.IdWorkerManage;
import com.example.NoteApp.util.RedisUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-11-25 10:57
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteMapper mapper;
    @Autowired
    private RedisUtil redisUtil;

    String cacheName = "allnote";

    public List<NoteEntity> findAll(){
        if (redisUtil.hasKey(cacheName)){
            return  redisUtil.get("allnote");
        }
        List<NoteEntity> list = mapper.findAll();
        redisUtil.set(cacheName,list);
        return list;
    }

    @Transactional
    public int insertNote(String title,String content,long createtime,long updatetime){
        redisUtil.del(cacheName);
        return mapper.insertNote(IdWorkerManage.getId(),title,content,createtime,updatetime);
    }

    @Transactional
    public int deleteNote(long id){
        redisUtil.del(cacheName);
        return mapper.delNote(id);
    }

    public NoteEntity getOne(long id){
        return mapper.getOne(id);
    }

    @Transactional
    public int doUpdate(NoteEntity noteEntity){
        redisUtil.del(cacheName);
        return mapper.doUpdate(noteEntity.getId(),noteEntity.getTitle(),noteEntity.getContent(), DateUtil.dateToLong());
    }
}
