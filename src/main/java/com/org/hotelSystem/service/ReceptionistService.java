package com.org.hotelSystem.service;

import com.org.hotelSystem.model.Parcel;

import java.sql.Timestamp;

public interface ReceptionistService {
    boolean checkInGuest(String userName, int bookingId);
    boolean checkOutGuest(String userName, int bookingId);
    boolean acceptParcel(Parcel parcel);
    boolean isParcelAvailable(String receiverName);

    boolean pickUpParcel(String parcelId, String receiverName);
}
