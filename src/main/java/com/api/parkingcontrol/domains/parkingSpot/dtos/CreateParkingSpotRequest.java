package com.api.parkingcontrol.domains.parkingSpot.dtos;

import com.api.parkingcontrol.domains.cars.Car;

public record CreateParkingSpotRequest(CreateParkingSpotDTO data, Car carData) {
}
