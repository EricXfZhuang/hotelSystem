package com.org.hotelSystem.mapper;

import com.org.hotelSystem.HotelSystemApplication;
import com.org.hotelSystem.model.RoomBooking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.Assert.*;


@SpringBootTest(classes = {HotelSystemApplication.class})
class RoomBookingMapperTest {

    @Autowired
    RoomBookingMapper roomBookingMapper;

    @Test
    void orderedTest(){
        insert();
        selectByIdAndGuestName();
        selectByIdAndTime();
    }

    @Test
    void insert() {
        roomBookingMapper.deleteAll();
        Timestamp start = Timestamp.valueOf("2015-10-02 18:45:05.123");
        Timestamp end = Timestamp.valueOf("2015-10-05 18:45:05.123");
        assertEquals(roomBookingMapper.insert(1, "david", start, end), 1);
    }


    void selectByIdAndGuestName() {
        RoomBooking roomBooking = roomBookingMapper.selectByName("david");
        assertNotNull(roomBooking);
        Integer roomId = roomBookingMapper.selectByIdAndGuestName(roomBooking.getBookingId(), "david");
        assertNotNull(roomId);
        roomId = roomBookingMapper.selectByIdAndGuestName(100, "david");
        assertNull(roomId);
    }

    void selectByIdAndTime() {
        int count = roomBookingMapper.selectByIdAndTime(1, Timestamp.valueOf("2015-10-02 00:00:00"), Timestamp.valueOf("2015-10-06 00:00:00"));
        assertTrue(count == 1);

        count = roomBookingMapper.selectByIdAndTime(1, Timestamp.valueOf("2015-09-02 00:00:00"), Timestamp.valueOf("2015-09-06 00:00:00"));
        assertTrue(count == 0);
    }
}