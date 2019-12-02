package com.example.NoteApp.activemq.producter;

import com.alibaba.fastjson.JSON;
import com.example.NoteApp.dto.ActiveModel;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author LiuZepeng
 * @date 2019-11-29 16:06
 */
@Component
public class ActivemqClient {
    @Autowired
    private JmsTemplate jmsTemplate;

    public enum Destinations{
        Common(1,"common")

        ;

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        Destinations(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public void send(ActiveModel model){
        String message = JSON.toJSONString(model);
        this.send(Destinations.Common,message);
    }

    public void send(Destinations destinations,ActiveModel model){
        String message = JSON.toJSONString(model);
        this.send(destinations,message);
    }

    private void send(Destinations destinations,String message) {
        Destination destination=new ActiveMQQueue(destinations.getName());
        jmsTemplate.convertAndSend(destination, message);
    }


}
