package com.getechnologiesMx.parking.model;

import com.getechnologiesMx.parking.dto.TypeVehicleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class TypeVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typevehicle_id")
    private Integer id;
    private String name;

    public TypeVehicle(TypeVehicleDTO typeVehicleDTO) {
        this.id = typeVehicleDTO.getId();
        this.name = typeVehicleDTO.getName();
    }



}
