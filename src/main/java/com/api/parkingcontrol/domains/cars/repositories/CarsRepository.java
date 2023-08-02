package com.api.parkingcontrol.domains.cars.repositories;

import com.api.parkingcontrol.domains.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarsRepository extends JpaRepository<Car, UUID> {
//    @Query("SELECT COUNT(c) > 0 FROM cars c WHERE c.license_plate = :carLicensePlate")
//    boolean existsByLicensePlate(String carLicensePlate);
}
