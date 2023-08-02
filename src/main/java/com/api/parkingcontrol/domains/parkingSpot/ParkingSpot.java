package com.api.parkingcontrol.domains.parkingSpot;

import com.api.parkingcontrol.domains.cars.Car;
import com.api.parkingcontrol.domains.parkingSpot.dtos.CreateParkingSpotRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "PARKING_SPOT")
@Table(name = "parking_spot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingSpot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String parking_spot_number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    @Column(nullable = false)
    private LocalDateTime registered_at;
    @Column(nullable = false, length = 130)
    private String owner_name;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;

    public ParkingSpot(CreateParkingSpotRequest createParkingSpotRequest) {
        this.car = createParkingSpotRequest.carData();
        this.parking_spot_number = createParkingSpotRequest.data().getParking_spot_number();
        this.owner_name = createParkingSpotRequest.data().getOwner_name();
        this.apartment = createParkingSpotRequest.data().getApartment();
        this.block = createParkingSpotRequest.data().getBlock();
    }
}
