package com.example.NoteApp.activemq.messageHandler;

import com.example.NoteApp.entity.NoteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-12-02 14:57
 */
@Component
public class NoticeHandler {

    public void doSomething(List<NoteEntity> list){
        for (NoteEntity e :
                list) {
            System.out.println(e);
        }
    }
}
