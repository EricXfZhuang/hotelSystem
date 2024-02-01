package com.org.hotelSystem.mapper;

import com.org.hotelSystem.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users(userName, password, name, phoneNumber, email, accountType) " +
            "VALUES(#{userName}, #{password}, #{name}, #{phoneNumber}, #{email}, #{accountType})")
    int insert(User user);
    @Select("SELECT * FROM users WHERE userName=#{userName}")
    User selectByUserName(String userName);

    @Delete("DELETE FROM users")
    void deleteAll();
}
