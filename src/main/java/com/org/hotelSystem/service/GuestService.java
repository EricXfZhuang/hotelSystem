package com.org.hotelSystem.service;

import com.org.hotelSystem.model.Guest;

import java.sql.Timestamp;
import java.util.Date;

public interface GuestService{
    boolean bookRoom(int roomId, String guestName, Timestamp startDate, Timestamp endDate);
}
