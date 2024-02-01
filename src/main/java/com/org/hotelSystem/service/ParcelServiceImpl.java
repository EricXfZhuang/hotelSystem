package com.org.hotelSystem.service;

import com.org.hotelSystem.enums.ParcelStatus;
import com.org.hotelSystem.mapper.ParcelMapper;
import com.org.hotelSystem.model.Parcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ParcelServiceImpl implements ParcelService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelServiceImpl.class);

    @Autowired
    ParcelMapper parcelMapper;

    @Override
    public boolean acceptParcel(Parcel parcel, Timestamp deliveredTime) {
        parcel.setDeliveredTime(deliveredTime);
        return parcelMapper.insert(parcel) != 0;
    }

    @Override
    public boolean pickUpParcel(String parcelId, Timestamp pickUpTime) {
        return parcelMapper.updateStatus(parcelId, pickUpTime, ParcelStatus.PICKED_UP.getCode()) != 0;
    }

    @Override
    public Parcel getParcel(String parcelId, String receiverName) {
        return parcelMapper.selectByIdAndReceiverName(parcelId, receiverName);
    }

    @Override
    public Parcel getParcel(String receiverName){
        return parcelMapper.selectByName(receiverName);
    }
}
