package com.example.NoteApp.mapper;

import com.example.NoteApp.entity.NoteEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoteMapper {

    @Select("select * from note where status = 1")
    List<NoteEntity> findAll();

    @Insert("insert into note(id,title,content,status,createtime,updatetime) values(#{id},#{title},#{content},1,#{createtime},#{updatetime}) ")
    int insertNote(long id,String title,String content,long createtime,long updatetime);

    @Delete("update note set status = 0 where id = #{id}")
    int delNote(long id);

    @Select("select * from note where id = #{id}")
    NoteEntity getOne(long id);

    @Update("update note set title=#{title},content=#{content},updatetime=#{updatetime} where id=#{id}")
    int doUpdate(long id,String title,String content,long updatetime);
}
