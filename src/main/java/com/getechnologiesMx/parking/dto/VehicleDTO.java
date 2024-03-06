package com.getechnologiesMx.parking.dto;

import com.getechnologiesMx.parking.model.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDTO {
    private Integer id;
    private String numberPlate;
    private TypeVehicleDTO typeVehicleDTO;


    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.numberPlate = vehicle.getNumberPlate();
        this.typeVehicleDTO = new TypeVehicleDTO(vehicle.getTypeVehicle());
    }


}
