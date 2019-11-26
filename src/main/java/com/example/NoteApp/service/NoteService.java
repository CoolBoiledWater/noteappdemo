package com.example.NoteApp.service;

import com.example.NoteApp.entity.NoteEntity;
import com.example.NoteApp.mapper.NoteMapper;
import com.example.NoteApp.util.DateUtil;
import com.example.NoteApp.util.IdWorkerManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-11-25 10:57
 */
@Service
public class NoteService {
    @Autowired
    private NoteMapper mapper;

    public List<NoteEntity> findAll(){
        return mapper.findAll();
    }

    @Transactional
    public int insertNote(String title,String content,long createtime,long updatetime){
        return mapper.insertNote(IdWorkerManage.getId(),title,content,createtime,updatetime);
    }

    @Transactional
    public int deleteNote(long id){
        return mapper.delNote(id);
    }

    public NoteEntity getOne(long id){
        return mapper.getOne(id);
    }

    @Transactional
    public int doUpdate(NoteEntity noteEntity){
        return mapper.doUpdate(noteEntity.getId(),noteEntity.getTitle(),noteEntity.getContent(), DateUtil.dateToLong());
    }
}
