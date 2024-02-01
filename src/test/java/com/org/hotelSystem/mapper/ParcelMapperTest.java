package com.org.hotelSystem.mapper;

import com.org.hotelSystem.HotelSystemApplication;
import com.org.hotelSystem.enums.ParcelStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.org.hotelSystem.model.Parcel;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest(classes = {HotelSystemApplication.class})
class ParcelMapperTest {

    @Autowired
    ParcelMapper parcelMapper;

    @Test
    void orderedTest(){
        insert();
        selectById();
        selectByIdAndReceiverName();
        updateStatus();
    }

    void insert() {
        parcelMapper.deleteAll();
        Parcel parcel = new Parcel("david", "GBGIETWO1234125", 1, Timestamp.valueOf("2015-10-02 18:45:05.123"));
        assertEquals(1, parcelMapper.insert(parcel));
    }


    void selectById(){
        Parcel parcel = parcelMapper.selectById("GBGIETWO1234125");
        assertNotNull(parcel);
        assertEquals("david", parcel.getReceiverName());
    }


    void selectByIdAndReceiverName() {
        Parcel parcel = parcelMapper.selectByIdAndReceiverName("GBGIETWO1234125", "david");
        assertNotNull(parcel);
        assertEquals(ParcelStatus.DELIVERED.getCode(), parcel.getStatus());
    }

    void updateStatus() {
        assertEquals(1, parcelMapper.updateStatus("GBGIETWO1234125", Timestamp.valueOf("2015-10-02 21:45:05.123"), ParcelStatus.PICKED_UP.getCode()));
        Parcel parcel = parcelMapper.selectById("GBGIETWO1234125");
        assertNotNull(parcel);
        assertEquals(ParcelStatus.PICKED_UP.getCode(), parcel.getStatus());
    }
}