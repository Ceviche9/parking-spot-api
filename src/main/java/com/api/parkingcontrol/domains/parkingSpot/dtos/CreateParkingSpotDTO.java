package com.api.parkingcontrol.domains.parkingSpot.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateParkingSpotDTO {
    @NotBlank
    private String parking_spot_number;
    @NotBlank
    private String owner_name;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;
    @NotBlank
    private String car_id;
}
