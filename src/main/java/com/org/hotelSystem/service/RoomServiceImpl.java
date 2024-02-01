package com.org.hotelSystem.service;

import com.org.hotelSystem.mapper.RoomMapper;
import com.org.hotelSystem.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomMapper roomMapper;

    @Override
    public boolean update(Room room) {
        return roomMapper.update(room) != 0;
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomMapper.selectRoomById(roomId);
    }
}