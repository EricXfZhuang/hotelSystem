package com.org.hotelSystem.controller;

import com.org.hotelSystem.model.RoomBooking;
import com.org.hotelSystem.service.GuestService;
import com.org.hotelSystem.service.ReceptionistService;
import com.org.hotelSystem.service.RoomBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Tag(name = "RoomController")
@RestController
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
    @Autowired
    private GuestService guestService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private RoomBookingService roomBookingService;


    /**
     * book a room with roomId and, guestName, startTime, endTime
     * return false if the room is not available between startTime and endTime
     * @param roomId
     * @param guestName
     * @param startTime
     * @param endTime
     * @return
     */
    @Parameters({
            @Parameter(name = "roomId", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "guestName", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "startTime", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "endTime", description = "", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "book a room with roomId and, guestName, startTime, endTime return false if the room is not available between startTime and endTime", description = "book a room with roomId and, guestName, startTime, endTime return false if the room is not available between startTime and endTime")
    @PostMapping(path = "/room/bookRoom")
    public boolean bookRoom(@RequestParam("roomId") int roomId,
                            @RequestParam("guestName") String guestName,
                            @RequestParam("startTime") Timestamp startTime,
                            @RequestParam("endTime") Timestamp endTime){
        if(!guestService.bookRoom(roomId, guestName, startTime, endTime)){
            LOGGER.error("Failed to book room {}", roomId);
            return false;
        }

        return true;
    }

    /**
     * checkin
     * @param userName
     * @param bookingId
     * @return
     */
    @Parameters({
            @Parameter(name = "userName", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "bookingId", description = "", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "checkin", description = "checkin")
    @PutMapping(path = "/room/checkIn")
    public boolean checkIn(@RequestParam("userName") String userName,
                           @RequestParam("bookingId") int bookingId){
        if(!receptionistService.checkInGuest(userName, bookingId)){
            return false;
        }
        return true;
    }

    /**
     * checkout
     * @param userName
     * @param bookingId
     * @return
     */
    @Parameters({
            @Parameter(name = "userName", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "bookingId", description = "", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "checkout", description = "checkout")
    @PutMapping(path = "/room/checkOut")
    public boolean checkOut(@RequestParam("userName") String userName,
                           @RequestParam("bookingId") int bookingId){
        if(!receptionistService.checkOutGuest(userName, bookingId)){
            return false;
        }
        return true;
    }

    @GetMapping(path="/room/getRoomBooking")
    public RoomBooking getRoom(@RequestParam("guestName") String guestName){
        return roomBookingService.getRoomBooking(guestName);
    }
}
