package com.getechnologiesMx.parking.model;

import com.getechnologiesMx.parking.dto.VehicleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String numberPlate;

    @ManyToOne
    @JoinColumn(name = "fk_typevehicle_id")
    private TypeVehicle typeVehicle;

    public Vehicle(VehicleDTO vehicleDTO) {
        this.id = vehicleDTO.getId();
        this.numberPlate = vehicleDTO.getNumberPlate();
        this.typeVehicle = new TypeVehicle(vehicleDTO.getTypeVehicleDTO());
    }


}
