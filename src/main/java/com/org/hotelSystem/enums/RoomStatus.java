package com.org.hotelSystem.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(title = "RoomStatus", example = "AVAILABLE=0")
public enum RoomStatus {
    @Schema(hidden = true) AVAILABLE(0),
    @Schema(hidden = true) NOT_AVAILABLE(1),
    @Schema(hidden = true) OCCUPIED(2);

    @Schema(hidden = true)
    private int code;
    @Schema(hidden = true)
    private static Map<Integer, RoomStatus> map = new HashMap();

    static{
        for(RoomStatus status : RoomStatus.values()){
            map.put(status.code, status);
        }
    }
    RoomStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static RoomStatus getType(int code){
        return map.get(code);
    }
}
