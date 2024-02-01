package com.org.hotelSystem.mapper;

import com.org.hotelSystem.model.RoomBooking;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface RoomBookingMapper {

    @Insert("INSERT INTO roomBookings(roomId, guestName, startTime, endTime) VALUES(#{roomId}, #{guestName}, #{startTime}, #{endTime})")
    int insert(int roomId, String guestName, Timestamp startTime, Timestamp endTime);
    @Select("SELECT count(*) FROM roomBookings " +
            "WHERE roomId=#{roomId} " +
            "AND ((startTime < #{startTime} AND #{startTime} < endTime) " +
            "OR (#{startTime} < startTime AND startTime < #{endTime}) " +
            "OR (startTime < #{endTime} && #{endTime} < endTime) " +
            "OR (#{startTime} < endTime && endTime < #{endTime}))")
    int selectByIdAndTime(int roomId, Timestamp startTime, Timestamp endTime);

    @Select("SELECT * from roomBookings WHERE guestName=#{guestName}")
    RoomBooking selectByName(String guestName);

    @Select("SELECT roomId from roomBookings WHERE bookingId=#{bookingId} AND guestName=#{guestName}")
    Integer selectByIdAndGuestName(int bookingId, String guestName);

    @Delete("DELETE FROM roomBookings")
    void deleteAll();
}
