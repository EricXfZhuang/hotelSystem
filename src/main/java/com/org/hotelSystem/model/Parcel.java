package com.org.hotelSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Schema(title="Parcel")
@Data
public class Parcel {

    /**
     * parcel id
     */
    @Schema(example="WQETHGOASHD1231")
    String parcelId;
    /**
     * receiver name
     */
    @Schema(example="david")
    String receiverName;
    /**
     * room id
     */
    @Schema(example="2")
    int roomId;
    /**
     * room status, please refer RoomStatus
     */
    @Schema(description = "room status, please refer RoomStatus", example="0")
    int status;
    /**
     * parcel delivered time
     */
    @Schema(example="2015-10-02 18:45:05.123")
    Timestamp deliveredTime;
    /**
     * parcel pickup time
     */
    @Schema(example="2015-10-02 18:45:05.123")
    Timestamp pickupTime;

    public Parcel(String parcelId, String receiverName, int roomId, int status, Timestamp deliveredTime, Timestamp pickupTime) {
        this.parcelId = parcelId;
        this.receiverName = receiverName;
        this.roomId = roomId;
        this.status = status;
        this.deliveredTime = deliveredTime;
        this.pickupTime = pickupTime;
    }

    public Parcel(String receiverName, String parcelId, int roomId, Timestamp deliveredTime) {
        this.deliveredTime = deliveredTime;
        this.roomId = roomId;
        this.receiverName = receiverName;
        this.parcelId = parcelId;
    }

    public Parcel(String parcelId, String receiverName, int roomId) {
        this.parcelId = parcelId;
        this.receiverName = receiverName;
        this.roomId = roomId;
    }
}
