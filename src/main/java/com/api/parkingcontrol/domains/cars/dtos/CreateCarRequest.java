package com.api.parkingcontrol.domains.cars.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCarRequest(
        @NotBlank
        String license_plate,
        @NotBlank
        String brand,
        @NotBlank
        String model,
        @NotBlank
         String color
) {
}
