package com.org.hotelSystem.mapper;

import com.org.hotelSystem.model.Parcel;

import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;


@Mapper
public interface ParcelMapper {
    @Insert("INSERT INTO parcels(parcelId, receiverName, roomId, status, deliveredTime) VALUES(#{parcelId}, #{receiverName}, #{roomId}, #{status}, #{deliveredTime})")
    int insert(Parcel parcel);

    @Update("UPDATE parcels SET pickupTime=#{pickUpTime}, status=#{parcelStatus} WHERE parcelId=#{parcelId}")
    int updateStatus(String parcelId, Timestamp pickUpTime, int parcelStatus);

    @Select("SELECT * FROM parcels WHERE parcelId=#{parcelId} AND receiverName=#{receiverName}")
    Parcel selectByIdAndReceiverName(String parcelId, String receiverName);

    @Select("SELECT * FROM parcels WHERE parcelId=#{parcelId}")
    Parcel selectById(String parcelId);

    @Select("SELECT * FROM parcels WHERE receiverName=#{receiverName}")
    Parcel selectByName(String receiverName);

    @Delete("DELETE FROM parcels")
    void deleteAll();
}
