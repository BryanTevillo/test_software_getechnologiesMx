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

    public Optional<VehicleDTO> oficcialRegisterVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleDTO.getNumberPlate().isEmpty() || vehicleDTO.getNumberPlate() != null) {
            VehicleDTO vehicleDtoSearch = findByNumberPlate(vehicleDTO.getNumberPlate());
            if (vehicleDtoSearch == null) {
                vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("Official"));
                return Optional.of(fabricVehicleService.createVehicleDto(
                        vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO))));
            } else {
                System.out.println("Ya existe un vehiculo con esa matricula");
                return Optional.empty();
            }
        } else {
            System.out.println("No se puede registrar vehiculo sin matricula");
            return Optional.empty();
        }
    }

    public Optional<VehicleDTO> residentRegisterVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleDTO.getNumberPlate().isEmpty() || vehicleDTO.getNumberPlate() != null) {
            VehicleDTO vehicleDtoSearch = findByNumberPlate(vehicleDTO.getNumberPlate());
            if (vehicleDtoSearch == null) {
                vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("Resident"));
                return Optional.of(fabricVehicleService.createVehicleDto(
                        vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO))));
            } else {
                System.out.println("Ya existe un vehiculo con esa matricula");
                return Optional.empty();
            }
        } else {
            System.out.println("No se puede registrar vehiculo sin matricula");
            return Optional.empty();
        }

    }

    public Optional<VehicleDTO> noResidentRegisterVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleDTO.getNumberPlate().isEmpty() || vehicleDTO.getNumberPlate() != null) {
            VehicleDTO vehicleDtoSearch = findByNumberPlate(vehicleDTO.getNumberPlate());
            if (vehicleDtoSearch == null) {
                vehicleDTO.setTypeVehicleDTO(typeVehicleService.findByName("NoResident"));
                return Optional.of(fabricVehicleService.createVehicleDto(
                        vehicleRepository.save(fabricVehicleService.createVehicle(vehicleDTO))));
            } else {
                System.out.println("Ya existe un vehiculo con esa matricula");
                return Optional.empty();
            }
        } else {
            System.out.println("No se puede registrar vehiculo sin matricula");
            return Optional.empty();
        }


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
