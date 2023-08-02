package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.domains.cars.Car;
import com.api.parkingcontrol.domains.cars.dtos.CreateCarRequest;
import com.api.parkingcontrol.domains.cars.useCases.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cars")
public class CarsController {
    final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity createCar(@RequestBody @Valid CreateCarRequest createCarRequest) {
        var carModel = new Car();
        BeanUtils.copyProperties(createCarRequest, carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(carModel));
    }
}
