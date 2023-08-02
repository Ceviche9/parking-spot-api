package com.api.parkingcontrol.domains.parkingSpot.useCases;

import com.api.parkingcontrol.domains.parkingSpot.ParkingSpot;
import com.api.parkingcontrol.domains.parkingSpot.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    // Caso aconteça algum erro, ele vai fazer o rollback.
    @Transactional
    public ParkingSpot save(ParkingSpot parkingSpot) {

        return parkingSpotRepository.save(parkingSpot);
    }

    public List<ParkingSpot> findAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpot findById(String id) {return parkingSpotRepository.getReferenceById(UUID.fromString(id));}

    public boolean existBySpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public void deleteById(String id) {
        parkingSpotRepository.deleteById(UUID.fromString(id));
    }
}
