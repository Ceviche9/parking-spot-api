package com.api.parkingcontrol.domains.cars.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarDTO {
    @NotBlank
    private String license_plate;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
}
