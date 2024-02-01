package com.org.hotelSystem.service;

import com.org.hotelSystem.model.Room;

import java.util.Date;

public interface RoomService {
    boolean update(Room room);

    Room getRoomById(int roomId);
}
