package com.getechnologiesMx.parking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.TypeVehicleDTO;

import com.getechnologiesMx.parking.model.TypeVehicle;


@Service
public class FabricTypeVehicleService {

    public TypeVehicle createTypeVehicle(TypeVehicleDTO typeVehicleDTO) {
        return new TypeVehicle(typeVehicleDTO);
    }

    public TypeVehicleDTO createTypeVehicleDto(TypeVehicle typeVehicle) {
        return new TypeVehicleDTO(typeVehicle);
    }

    public List<TypeVehicleDTO> createTypeVehiclesDto(List<TypeVehicle> typeVehicles) {
        List<TypeVehicleDTO> typeVehiclesDto = new ArrayList<>();
        typeVehicles.forEach(typeVehicle -> {
            typeVehiclesDto.add(new TypeVehicleDTO(typeVehicle));
        });

        return typeVehiclesDto;
    }
}
