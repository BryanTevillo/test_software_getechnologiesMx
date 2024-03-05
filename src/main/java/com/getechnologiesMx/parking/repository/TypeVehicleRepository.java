package com.getechnologiesMx.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.getechnologiesMx.parking.model.TypeVehicle;

@Repository
public interface TypeVehicleRepository extends JpaRepository<TypeVehicle, Integer> {

}
