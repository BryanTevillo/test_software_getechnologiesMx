package com.getechnologiesMx.parking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.TypeVehicleDTO;
import com.getechnologiesMx.parking.repository.TypeVehicleRepository;


@Service
public class TypeVehicleService {

    @Autowired
    private TypeVehicleRepository typeVehicleRepository;
    @Autowired
    private FabricTypeVehicleService fabricTypeVehicleService;
    @Autowired
    FabricVehicleService fabricVehicleService;

    public List<TypeVehicleDTO> findAll() {
        return fabricTypeVehicleService.createTypeVehiclesDto(typeVehicleRepository.findAll());
    }


    public TypeVehicleDTO findByName(String name) {
        List<TypeVehicleDTO> typeVehiclesDto =
                fabricTypeVehicleService.createTypeVehiclesDto(typeVehicleRepository.findAll());
        Optional<TypeVehicleDTO> typeVehicleDto = typeVehiclesDto.stream()
                .filter(singleTypeVehicleDto -> singleTypeVehicleDto.getName().equals(name))
                .findFirst();

        return typeVehicleDto.orElse(null);
    }

    public TypeVehicleDTO saveTypeVehicleDTO(TypeVehicleDTO typeVehicleDto) {
        return fabricTypeVehicleService.createTypeVehicleDto(typeVehicleRepository
                .save(fabricTypeVehicleService.createTypeVehicle(typeVehicleDto)));
    }
}
