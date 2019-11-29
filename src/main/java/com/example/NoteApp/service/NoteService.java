package com.example.NoteApp.service;

import com.example.NoteApp.entity.NoteEntity;

import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-11-28 16:11
 */
public interface NoteService {

    public List<NoteEntity> findAll();

    public int insertNote(String title,String content,long createtime,long updatetime);

    public int deleteNote(long id);

    public NoteEntity getOne(long id);

    public int doUpdate(NoteEntity noteEntity);


}
