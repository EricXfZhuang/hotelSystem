package com.org.hotelSystem.mapper;

import com.org.hotelSystem.HotelSystemApplication;
import com.org.hotelSystem.enums.AccountType;
import com.org.hotelSystem.model.Guest;
import com.org.hotelSystem.model.Receptionist;
import com.org.hotelSystem.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {HotelSystemApplication.class})
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void orderedTest(){
        insert();
        selectByUserName();
    }

    void insert() {
        userMapper.deleteAll();

        User user = new User("david", "abc", "123", AccountType.GUEST.getCode(), 1236542356, "abceft.234@example.com");
        userMapper.insert(user);

        Guest guest = new Guest("john", "xyz", "123", 786542356, "xyzeft.234@example.com");
        assertEquals(AccountType.GUEST.getCode(), guest.getAccountType());
        userMapper.insert(guest);

        Receptionist receptionist = new Receptionist("jennie", "wqbghsoiau", "123", 1236542356, "wrweqeft.234@example.com");
        assertEquals(AccountType.RECEPTIONIST.getCode(), receptionist.getAccountType());
        userMapper.insert(receptionist);
    }

    void selectByUserName() {
        User user = userMapper.selectByUserName("abc");
        assertNotNull(user);

        user = userMapper.selectByUserName("notexist");
        assertNull(user);
    }
}