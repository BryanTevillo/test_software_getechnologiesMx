package com.getechnologiesMx.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.getechnologiesMx.parking.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
