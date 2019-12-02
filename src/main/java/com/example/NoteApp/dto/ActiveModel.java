package com.example.NoteApp.dto;

import com.alibaba.fastjson.JSON;
import com.example.NoteApp.dto.Enum.ActiveHearder;
import lombok.Data;

/**
 * @author LiuZepeng
 * @date 2019-11-29 17:36
 */
@Data
public class ActiveModel {
    private ActiveHearder hearder;
    private String data;

    public ActiveModel(ActiveHearder hearder,Object datas) {
        this.hearder = hearder;
        this.data= JSON.toJSONString(datas);
    }

    public ActiveModel() {
    }
}
