package com.getechnologiesMx.parking.service;

import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.VehicleDTO;
import com.getechnologiesMx.parking.model.Vehicle;

@Service
public class FabricVehicleService {

    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        return new Vehicle(vehicleDTO);
    }

    public VehicleDTO createVehicleDto(Vehicle vehicle) {
        return new VehicleDTO(vehicle);
    }
}
