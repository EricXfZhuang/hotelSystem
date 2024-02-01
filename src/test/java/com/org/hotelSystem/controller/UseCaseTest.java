package com.org.hotelSystem.controller;

import com.org.hotelSystem.HotelSystemApplication;
import com.org.hotelSystem.enums.AccountType;
import com.org.hotelSystem.enums.ParcelStatus;
import com.org.hotelSystem.enums.RoomStatus;
import com.org.hotelSystem.mapper.ParcelMapper;
import com.org.hotelSystem.mapper.RoomBookingMapper;
import com.org.hotelSystem.mapper.RoomMapper;
import com.org.hotelSystem.mapper.UserMapper;
import com.org.hotelSystem.model.Parcel;
import com.org.hotelSystem.model.Room;
import com.org.hotelSystem.model.RoomBooking;
import com.org.hotelSystem.model.User;
import com.org.hotelSystem.service.ParcelService;
import com.org.hotelSystem.service.RoomBookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@SpringBootTest(classes = {HotelSystemApplication.class})
class UseCaseTest {
    @Autowired
    UserController userController;
    @Autowired
    RoomController roomController;
    @Autowired
    ParcelController parcelController;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ParcelMapper parcelMapper;
    @Autowired
    RoomBookingMapper roomBookingMapper;
    @Autowired
    ParcelService parcelService;

    @Test
    void test1(){
        roomMapper.deleteAll();
        roomBookingMapper.deleteAll();
        parcelMapper.deleteAll();
        userMapper.deleteAll();

        // insert a room record to the room table
        Room room = new Room(1, RoomStatus.AVAILABLE.getCode());
        assertEquals(1, roomMapper.insert(room));

        // David register a user account
        User user = new User("david", "abc", "123", AccountType.GUEST.getCode(), 1236542356, "abceft.234@example.com");
        assertTrue(userController.register(user));

        Timestamp start = Timestamp.valueOf("2015-10-02 18:45:05.123");
        Timestamp end = Timestamp.valueOf("2015-10-05 18:45:05.123");

        // David books a room
        assertTrue(roomController.bookRoom(1, "david", start, end));
        RoomBooking roomBooking = roomController.getRoom("david");
        assertNotNull(roomBooking);
        int bookingId = roomBooking.getBookingId();

        // A parcel delivered before David checked in will not be accepted.
        Parcel parcel = new Parcel("david", "GBGIETWO1234125", 1, Timestamp.valueOf("2015-10-01 18:45:05.123"));
        assertFalse(parcelController.acceptParcel(parcel));

        // David checked in
        roomController.checkIn("abc", bookingId);
        room = roomMapper.selectRoomById(1);
        assertEquals(RoomStatus.OCCUPIED.getCode(), room.getStatus());

        // Now the parcel will be accepted because david has checked in already. Parcel status is DELIVERED
        parcel = new Parcel("david", "GBGIETWO1234125", 1, Timestamp.valueOf("2015-10-02 18:45:05.127"));
        assertTrue(parcelController.acceptParcel(parcel));
        parcel = parcelService.getParcel("david");
        assertNotNull(parcel);
        assertEquals(ParcelStatus.DELIVERED.getCode(), parcel.getStatus());

        // David picked up the parcel, the parcel status updated to PICKED_UP
        assertTrue(parcelController.pickUpParcel("GBGIETWO1234125", "david"));
        parcel = parcelService.getParcel("david");
        assertEquals(ParcelStatus.PICKED_UP.getCode(), parcel.getStatus());

        // David checks out, room status became AVAILABLE
        roomController.checkOut("abc", bookingId);
        room = roomMapper.selectRoomById(1);
        assertEquals(RoomStatus.AVAILABLE.getCode(), room.getStatus());

        roomMapper.deleteAll();
        roomBookingMapper.deleteAll();
        parcelMapper.deleteAll();
        userMapper.deleteAll();
    }
}