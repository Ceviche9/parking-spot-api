package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.domains.parkingSpot.dtos.CreateParkingSpotDTO;
import com.api.parkingcontrol.domains.parkingSpot.dtos.CreateParkingSpotRequest;
import com.api.parkingcontrol.domains.parkingSpot.ParkingSpot;
import com.api.parkingcontrol.domains.cars.useCases.CarService;
import com.api.parkingcontrol.domains.parkingSpot.useCases.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;
    final CarService carService;

    public ParkingSpotController(ParkingSpotService parkingSpotService, CarService carService) {
        this.parkingSpotService = parkingSpotService;
        this.carService = carService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid CreateParkingSpotDTO createParkingSpotDTO) {
        System.out.println("Entrei");
        if (parkingSpotService.existBySpotNumber(createParkingSpotDTO.getParking_spot_number())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot number is already in use!");
        }

        if (parkingSpotService.existByApartmentAndBlock(createParkingSpotDTO.getApartment(), createParkingSpotDTO.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartment and block is already in use!");
        }

        var car = carService.getReferenceById(createParkingSpotDTO.getCar_id());
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found!");
        }
        var createParkingSpotRequest = new CreateParkingSpotRequest(createParkingSpotDTO, car);
        var parkingSpotModel = new ParkingSpot(createParkingSpotRequest);
        // Essa data o cliente não envia, então esse é o momento em que o model irá receber ela.
        parkingSpotModel.setRegistered_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpot>> getAllParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") String id) {
        var response = parkingSpotService.findById(id);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOne(@PathVariable(value = "id") String id) {
        var response = parkingSpotService.findById(id);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found!");
        parkingSpotService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
