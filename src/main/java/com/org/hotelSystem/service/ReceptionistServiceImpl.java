package com.org.hotelSystem.service;

import com.org.hotelSystem.enums.ParcelStatus;
import com.org.hotelSystem.enums.RoomStatus;
import com.org.hotelSystem.model.Parcel;
import com.org.hotelSystem.model.Room;
import com.org.hotelSystem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ReceptionistServiceImpl implements ReceptionistService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceptionistServiceImpl.class);
    @Autowired
    RoomService roomService;

    @Autowired
    ParcelService parcelService;

    @Autowired
    UserService userService;

    @Autowired
    RoomBookingService roomBookingService;

    @Override
    public boolean checkInGuest(String userName, int bookingId) {
        User user = userService.getUser(userName);
        if(user == null){
            LOGGER.error("User {} does not exist", userName);
            return false;
        }
        Room room = roomBookingService.getRoom(bookingId, user.getName());
        if(room == null){
            LOGGER.error("No room found with bookingId {}, guest_name {}", user.getName());
            return false;
        }
        room.setGuestName(user.getName());
        room.setStatus(RoomStatus.OCCUPIED.getCode());
        if(!roomService.update(room)){
            LOGGER.error("Updating room status failed for room {}", room.getRoomId());
            return false;
        }
        return true;
    }

    @Override
    public boolean checkOutGuest(String userName, int bookingId) {
        User user = userService.getUser(userName);
        if(user == null){
            LOGGER.error("User {} does not exist", userName);
            return false;
        }
        Room room = roomBookingService.getRoom(bookingId, user.getName());
        room.setGuestName(user.getName());
        room.setStatus(RoomStatus.AVAILABLE.getCode());
        if(!roomService.update(room)){
            LOGGER.error("Updating room status failed for room {}", room.getRoomId());
            return false;
        }
        return true;
    }

    @Override
    public boolean acceptParcel(Parcel parcel) {
        Timestamp deliveredTime = new Timestamp(System.currentTimeMillis());
        if(!isAcceptable(parcel.getRoomId(), parcel.getReceiverName())){
            return false;
        }
        if(!parcelService.acceptParcel(parcel, deliveredTime)){
            LOGGER.error("Cannot accept parcel {}", parcel.getParcelId());
            return false;
        }
        return true;
    }

    @Override
    public boolean isParcelAvailable(String receiverName) {
        Parcel parcel =  parcelService.getParcel(receiverName);
        return parcel != null && parcel.getStatus() == ParcelStatus.DELIVERED.getCode();
    }

    @Override
    public boolean pickUpParcel(String parcelId, String receiverName) {
        Timestamp pickUpTime = new Timestamp(System.currentTimeMillis());
        Parcel parcel = parcelService.getParcel(parcelId, receiverName);
        if(parcel == null){
            LOGGER.error("No parcel found with parcelId {}", parcelId);
            return false;
        }
        parcelService.pickUpParcel(parcelId, pickUpTime);
        return true;
    }

    private boolean isAcceptable(int roomId, String receiverName){
        Room room = roomService.getRoomById(roomId);
        if(room == null){
            LOGGER.error("No room found with roomId {}", roomId);
            return false;
        }
        if(room.getStatus() != RoomStatus.OCCUPIED.getCode() || !room.getGuestName().equals(receiverName)){
            LOGGER.error("Receiver {} has not checked in", receiverName);
            return false;
        }
        return true;
    }
}
