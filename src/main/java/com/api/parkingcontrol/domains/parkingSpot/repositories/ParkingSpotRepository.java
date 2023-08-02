package com.api.parkingcontrol.domains.parkingSpot.repositories;

import com.api.parkingcontrol.domains.parkingSpot.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {
    @Query("SELECT COUNT(p) > 0 FROM PARKING_SPOT p WHERE p.parking_spot_number = :parkingSpotNumber")
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    @Query("SELECT COUNT(p) > 0 FROM PARKING_SPOT p WHERE p.apartment = :apartment AND p.block = :block")
    boolean existsByApartmentAndBlock(String apartment, String block);
}
