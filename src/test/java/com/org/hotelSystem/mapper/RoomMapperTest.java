package com.org.hotelSystem.mapper;

import com.org.hotelSystem.HotelSystemApplication;
import com.org.hotelSystem.enums.RoomStatus;
import com.org.hotelSystem.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = {HotelSystemApplication.class})
class RoomMapperTest {
    @Autowired
    RoomMapper roomMapper;

    @Test
    void orderedTest(){
        insert();
        selectRoomById();
        update();
    }


    void insert() {
        roomMapper.deleteAll();
        Room room = new Room(1, RoomStatus.AVAILABLE.getCode());
        assertEquals(1, roomMapper.insert(room));
    }

    void selectRoomById() {
        Room room = roomMapper.selectRoomById(1);
        assertEquals(1, room.getRoomId());
    }

    void update() {
        Room room = roomMapper.selectRoomById(1);
        room.setStatus(RoomStatus.NOT_AVAILABLE.getCode());
        room.setGuestName("david");
        assertEquals(1, roomMapper.update(room));
        room = roomMapper.selectRoomById(1);
        assertEquals(room.getStatus(), RoomStatus.NOT_AVAILABLE.getCode());

    }
}