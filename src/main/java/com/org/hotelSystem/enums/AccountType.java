package com.org.hotelSystem.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Schema(title="AccountType", example = "GUEST=0")
public enum AccountType {
    @Schema(hidden = true) GUEST(0),
    @Schema(hidden = true) RECEPTIONIST(1),
    @Schema(hidden = true) MANAGER(2),
    @Schema(hidden = true) HOUSEKEEPER(3),
    @Schema(hidden = true) ADMIN(4);

    @Schema(hidden = true)
    private int code;
    @Schema(hidden = true)
    private static Map<Integer, AccountType> map = new HashMap();

    static{
        for(AccountType accountType : AccountType.values()){
            map.put(accountType.code, accountType);
        }
    }
    AccountType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static AccountType getType(int code){
        return map.get(code);
    }
}
