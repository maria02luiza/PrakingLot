package com.parking.prakinglot.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Cars> cars = new ArrayList<>();

    @Column(name = "parking_spot")
    private String parkingSpot;

    @Column(name = "license_plate")
    private String licensePlate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Cars owner;

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    public Cars getOwner() {
        return owner;
    }

    public void setOwner(Cars owner) {
        this.owner = owner;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}