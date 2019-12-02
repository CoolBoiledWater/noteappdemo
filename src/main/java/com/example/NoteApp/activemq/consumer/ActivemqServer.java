package com.example.NoteApp.activemq.consumer;


import com.alibaba.fastjson.JSON;
import com.example.NoteApp.activemq.messageHandler.NoticeHandler;
import com.example.NoteApp.dto.ActiveModel;
import com.example.NoteApp.dto.Enum.ActiveHearder;
import com.example.NoteApp.entity.NoteEntity;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author LiuZepeng
 * @date 2019-11-29 16:06
 */
@Component
public class ActivemqServer {
    @Autowired
    private NoticeHandler noticeHandler;

    @JmsListener(destination = "common", containerFactory = "queueListenerFactory")
    @SuppressWarnings("unchecked")
    public void receiveMsg(String text) {
        ActiveModel activeModel = JSON.parseObject(text, ActiveModel.class);
        String header = activeModel.getHearder().getName();
        String activeModelData = activeModel.getData();
        switch (header) {
            case "comment":
                noticeHandler.doSomething((JSON.parseArray(activeModelData,NoteEntity.class)));
                break;
            case "notice":

                break;
            default:
                break;
        }


        System.out.println("<<<<<<============ 收到消息： " + text);
    }
}
