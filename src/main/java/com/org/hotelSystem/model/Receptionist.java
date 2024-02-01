package com.org.hotelSystem.model;

import com.org.hotelSystem.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

@Schema(title="Receptionist")
public class Receptionist extends User{

    public Receptionist(@NonNull String name, @NonNull String userName, @NonNull String password, @NonNull Integer phoneNumber, @NonNull String email) {
        super(name, userName, password, AccountType.RECEPTIONIST.getCode(), phoneNumber, email);
    }
}
