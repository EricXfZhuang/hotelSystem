package com.org.hotelSystem.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(title = "ParcelStatus", example = "DELIVERED=0")
public enum ParcelStatus {
    @Schema(hidden = true) DELIVERED(0),
    @Schema(hidden = true) PICKED_UP(1);

    @Schema(hidden = true)
    private int code;
    @Schema(hidden = true)
    private static Map<Integer, ParcelStatus> map = new HashMap();

    static{
        for(ParcelStatus status : ParcelStatus.values()){
            map.put(status.code, status);
        }
    }
    ParcelStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static ParcelStatus getType(int code){
        return map.get(code);
    }
}
