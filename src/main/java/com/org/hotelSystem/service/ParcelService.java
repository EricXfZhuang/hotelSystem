package com.org.hotelSystem.service;
import com.org.hotelSystem.model.*;

import java.sql.Timestamp;

public interface ParcelService {
    boolean acceptParcel(Parcel parcel, Timestamp deliveredTime);
    boolean pickUpParcel(String parcelId, Timestamp pickUpTime);

    Parcel getParcel(String parcelId, String receiverName);
    Parcel getParcel(String receiverName);
}
