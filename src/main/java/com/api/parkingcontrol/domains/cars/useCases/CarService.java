package com.api.parkingcontrol.domains.cars.useCases;

import com.api.parkingcontrol.domains.cars.Car;
import com.api.parkingcontrol.domains.cars.repositories.CarsRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {

    final CarsRepository carsRepository;

    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Car getReferenceById(String id) {
        return carsRepository.getReferenceById(UUID.fromString(id));
    }

    public Object create(Car carModel) {
        return carsRepository.save(carModel);
    }

//    public boolean existByLicensePlate(String carLicensePlate) {
//        return carsRepository.existsByLicensePlate(carLicensePlate);
//    }
}
