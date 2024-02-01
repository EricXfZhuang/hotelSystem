package com.org.hotelSystem.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Schema(title="RoomBooking")
@Data
public class RoomBooking {

    @Schema(example="1")
    int bookingId;

    @Schema(example="1")
    @NonNull
    int roomId;

    @Schema(example="david")
    @NonNull
    String guestName;

    @Schema(example="2015-10-02 18:45:05.123")
    @NonNull
    Timestamp startTime;

    @Schema(example="2015-10-02 18:45:05.123")
    @NonNull
    Timestamp endTime;

    public RoomBooking(int bookingId, int roomId, String guestName, Timestamp startTime, Timestamp endTime) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.guestName = guestName;
    }
    public RoomBooking(int roomId, String guestName, Timestamp startTime, Timestamp endTime) {
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.guestName = guestName;
    }
}
