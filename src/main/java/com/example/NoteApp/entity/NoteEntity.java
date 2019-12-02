package com.example.NoteApp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiuZepeng
 * @date 2019-11-25 10:09
 */
@Data
public class NoteEntity implements Serializable {
    private long id;
    private String title="";
    private String content="";
    private int status;
    private long createtime;
    private long updatetime;

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
