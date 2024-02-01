package com.org.hotelSystem.mapper;

import com.org.hotelSystem.model.Room;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoomMapper {

    @Select("SELECT * from rooms WHERE roomId=#{roomId}")
    Room selectRoomById(int roomId);

    @Update("UPDATE rooms SET guestName=#{guestName}, status=#{status}")
    int update(Room room);

    @Insert("INSERT INTO rooms(roomId, status) VALUES(#{roomId}, #{status})")
    int insert(Room room);

    @Delete("DELETE FROM rooms")
    void deleteAll();
}
