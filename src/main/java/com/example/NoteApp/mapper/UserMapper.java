package com.example.NoteApp.mapper;

import com.example.NoteApp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from tb_user where status = 1 and user_name = #{userName} and user_password = #{userPassword}")
     User getUserByUserName(String userName,String userPassword);
}
