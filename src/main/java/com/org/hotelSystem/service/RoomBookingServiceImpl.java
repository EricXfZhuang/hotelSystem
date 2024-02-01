package com.org.hotelSystem.service;

import com.org.hotelSystem.mapper.RoomBookingMapper;
import com.org.hotelSystem.model.Room;
import com.org.hotelSystem.model.RoomBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RoomBookingServiceImpl implements RoomBookingService{
    @Autowired
    RoomBookingMapper roomBookingMapper;

    @Autowired
    RoomService roomService;

    @Override
    public boolean bookRoom(int roomId, String guestName, Timestamp startTime, Timestamp endTime) {
        return roomBookingMapper.insert(roomId, guestName, startTime, endTime) != 0;
    }

    @Override
    public boolean checkRoomAvailability(int roomId, Timestamp startTime, Timestamp endTime) {
        return roomBookingMapper.selectByIdAndTime(roomId, startTime, endTime) == 0;
    }

    @Override
    public Room getRoom(int bookingId, String guestName) {
        Integer roomId = roomBookingMapper.selectByIdAndGuestName(bookingId, guestName);
        if(roomId == null){
            return null;
        }
        return roomService.getRoomById(roomId);
    }

    @Override
    public RoomBooking getRoomBooking(String guestName) {
        return roomBookingMapper.selectByName(guestName);
    }
}
