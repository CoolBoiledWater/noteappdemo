package com.example.NoteApp.configer;

import com.example.NoteApp.util.DateUtil;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author LiuZepeng
 * @date 2019-11-25 15:00
 */
public class freemarkerconf implements TemplateMethodModelEx {

    @Override
    public Object exec(List args) throws TemplateModelException {
        int size = args.size();
        if (size < 1) {
            throw new TemplateModelException("参数错误!");
        }
        Object obj = args.get(0);
        if (obj == null) {
            return "";
        }
        String date = obj.toString();
        if (date.startsWith("9999")) {
            return date;
        }
        date += "00000000000000";
            long longdate = Long.parseLong(date.substring(0, 14));
            if(longdate <= 9999){
                throw new TemplateModelException("时间错误!");
            }
            if(size == 1){
                return DateUtil.longToString(longdate);
            }
            if(size == 2){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime parse = LocalDateTime.parse(longdate + "", formatter);
                return DateTimeFormatter.ofPattern(args.get(1).toString()).format(parse);
            }
        return date;
    }
}
