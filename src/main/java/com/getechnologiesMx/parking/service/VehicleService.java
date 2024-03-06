package com.getechnologiesMx.parking.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.VehicleDTO;
import com.getechnologiesMx.parking.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private FabricVehicleService fabricVehicleService;
    @Autowired
    TypeVehicleService typeVehicleService;

    public VehicleDTO oficcialRegisterVehicle(VehicleDTO vehicleDTO) {

        vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("Official"));
        return fabricVehicleService.createVehicleDto(
                vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO)));
    }

    public VehicleDTO residentRegisterVehicle(VehicleDTO vehicleDTO) {

        vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("Resident"));
        return fabricVehicleService.createVehicleDto(
                vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO)));
    }

    public VehicleDTO noResidentRegisterVehicle(VehicleDTO vehicleDTO) {

        vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("NoResident"));
        return fabricVehicleService.createVehicleDto(
                vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO)));
    }

    public VehicleDTO findByNumberPlate(String numberPlate) {
        List<VehicleDTO> vehiclesDto =
                fabricVehicleService.createVehiclesDto(vehicleRepository.findAll());
        Optional<VehicleDTO> vehicleDto = vehiclesDto.stream()
                .filter(singleVehicleDto -> singleVehicleDto.getNumberPlate().equals(numberPlate))
                .findFirst();

        return vehicleDto.orElse(null);
    }


}
