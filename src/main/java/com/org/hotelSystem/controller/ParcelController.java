package com.org.hotelSystem.controller;

import com.org.hotelSystem.model.Parcel;
import com.org.hotelSystem.service.ParcelService;
import com.org.hotelSystem.service.ReceptionistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "ParcelController")
@RestController
public class ParcelController {

    @Autowired
    ParcelService parcelService;

    @Autowired
    ReceptionistService receptionistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelController.class);

    /**
     * Accept a parcel
     * @param parcel
     * @return
     */
    @Operation(summary = "Accept a parcel", description = "Accept a parcel")
    @PostMapping(path = "/parcel/acceptParcel")
    public boolean acceptParcel(@RequestBody Parcel parcel){
        if(!receptionistService.acceptParcel(parcel)){
            return false;
        }
        return true;
    }

    /**
     * pick up a Parcel with the parcelId and receiver name
     * @param parcelId
     * @param receiverName
     * @return
     */
    @Parameters({
            @Parameter(name = "parcelId", description = "", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "receiverName", description = "", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "pick up a Parcel with the parcelId and receiver name", description = "pick up a Parcel with the parcelId and receiver name")
    @PutMapping(path = "/parcel/pickUpParcel")
    public boolean pickUpParcel(@RequestParam("parcelId") String parcelId,
                                @RequestParam("receiverName") String receiverName){
        if(!receptionistService.pickUpParcel(parcelId, receiverName)){
            return false;
        }
        return true;
    }

    /**
     * check the availability of the parcel given the receiver name
     * @param receiverName
     * @return
     */
    @Parameter(name = "receiverName", description = "", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "check the availability of the parcel given the receiver name", description = "check the availability of the parcel given the receiver name")
    @GetMapping(path = "/parcel/checkParcel")
    public boolean checkParcelAvailability(@RequestParam("receiverName") String receiverName){
        return receptionistService.isParcelAvailable(receiverName);
    }
}
