package com.getechnologiesMx.parking.dto;


import com.getechnologiesMx.parking.model.TypeVehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeVehicleDTO {

    private Integer id;
    private String name;

    public TypeVehicleDTO(TypeVehicle typeVehicle) {
        this.id = typeVehicle.getId();
        this.name = typeVehicle.getName();
    }



}
