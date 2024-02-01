package com.org.hotelSystem.service;

import com.org.hotelSystem.controller.RoomController;
import com.org.hotelSystem.model.RoomBooking;
import com.org.hotelSystem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class GuestServiceImpl implements GuestService{
    private static final Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class);
    @Autowired
    private RoomBookingService roomBookingService;

    @Override
    public boolean bookRoom(int roomId, String guestName, Timestamp startTime, Timestamp endTime) {
        if(!roomBookingService.checkRoomAvailability(roomId, startTime, endTime)){
            LOGGER.error("room {} is not available", roomId);
            return false;
        }
        if(!roomBookingService.bookRoom(roomId, guestName, startTime, endTime)){
            LOGGER.error("Failed to book room {}", roomId);
            return false;
        }
        return true;
    }
}
