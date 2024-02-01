package com.org.hotelSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Schema(title="Room")
@Data
public class Room {

    @Schema(example="2")
    @NonNull
    int roomId;

    @Schema(example="0")
    @NonNull
    int status;

    @Schema(example="david")
    String guestName;

    @Schema(example="1")
    int bookingId;

    public Room(@NonNull int roomId, @NonNull int status) {
        this.roomId = roomId;
        this.status = status;
    }

}
