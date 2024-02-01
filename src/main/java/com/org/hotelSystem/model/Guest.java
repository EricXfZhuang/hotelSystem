package com.org.hotelSystem.model;

import com.org.hotelSystem.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

@Schema(title="Guest")
public class Guest extends User{


    public Guest(@NonNull String name, @NonNull String userName, @NonNull String password, @NonNull Integer phoneNumber, @NonNull String email) {
        super(name, userName, password, AccountType.GUEST.getCode(), phoneNumber, email);
    }
}
