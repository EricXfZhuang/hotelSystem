package com.org.hotelSystem.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

@Schema
@Data
public class User {
    @Schema(example="david")
    @NonNull
    String name;

    @Schema(example="davidabc")
    @NonNull
    String userName;

    @Schema(example = "1244thfd")
    @NonNull
    String password;

    @Schema(example="0")
    @NonNull
    int accountType;

    @Schema(example="355129876105")
    @NonNull
    Integer phoneNumber;

    @Schema(example = "giwert.gieth@example.com")
    @NonNull
    String email;

    public User(@NonNull String name, @NonNull String userName, @NonNull String password, @NonNull int accountType, @NonNull Integer phoneNumber, @NonNull String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


}
