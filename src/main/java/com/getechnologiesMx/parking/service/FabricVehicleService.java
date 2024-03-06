package com.getechnologiesMx.parking.service;

import java.util.ArrayList;
import java.util.List;
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

    public List<VehicleDTO> createVehiclesDto(List<Vehicle> vehicles) {
        List<VehicleDTO> vehiclesDto = new ArrayList<>();
        vehicles.forEach(vehicle -> {
            vehiclesDto.add(new VehicleDTO(vehicle));
        });

        return vehiclesDto;
    }
}
