package com.org.hotelSystem.service;

import com.org.hotelSystem.model.Room;
import com.org.hotelSystem.model.RoomBooking;

import java.sql.Timestamp;
import java.util.Date;

public interface RoomBookingService {
    boolean bookRoom(int roomId, String guestName, Timestamp startDate, Timestamp endDate);

    boolean checkRoomAvailability(int roomId, Timestamp startDate, Timestamp endDate);

    Room getRoom(int bookingId, String guestName);
    RoomBooking getRoomBooking(String guestName);
}
