package com.getechnologiesMx.parking.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.StayDTO;
import com.getechnologiesMx.parking.dto.VehicleDTO;

import com.getechnologiesMx.parking.repository.StayRepository;
import com.getechnologiesMx.parking.util.Constants;

@Service
public class StayService {

    @Autowired
    private StayRepository stayRepository;
    @Autowired
    FabricStayService fabricStayService;
    @Autowired
    private VehicleService vehicleService;


    public Optional<StayDTO> registerEntry(VehicleDTO vehicleDTO) {
        // Obtener el vehículo por número de placa
        VehicleDTO vehicleDtoSave = vehicleService.findByNumberPlate(vehicleDTO.getNumberPlate());
        StayDTO stayDto = new StayDTO();

        if (vehicleDtoSave != null) {
            StayDTO stayDtoSearch = findByVehicle(vehicleDtoSave);
            if (stayDtoSearch == null) {
                // Lógica para registrar la entrada de un vehículo
                stayDto.setVehicleDTO(vehicleDtoSave);
                stayDto.setTimeEntry(LocalDateTime.now());
                System.out.println("Entrada registrada exitosamente.");
                return Optional.of(fabricStayService
                        .createStayDto(stayRepository.save(fabricStayService.createStay(stayDto))));
            } else {
                System.out
                        .println("Ya hay un registro de entrada, no se puede registrar nuevamente");
                return Optional.empty();
            }
        }

        else {
            System.out.println("No se encontro Vehiculo registrado");
            return Optional.empty();
        }

    }

    public Optional<StayDTO> registerDeparture(VehicleDTO vehicleDto) {

        // Obtener el vehículo por número de placa
        VehicleDTO vehicleDtoSave = vehicleService.findByNumberPlate(vehicleDto.getNumberPlate());
        if (vehicleDtoSave != null) {
            StayDTO stayDto = findByVehicle(vehicleDtoSave);
            if (stayDto != null) {
                if (stayDto.getTimeDeparture() == null) {
                    stayDto.setVehicleDTO(vehicleDtoSave);
                    stayDto.setTimeDeparture(LocalDateTime.now());
                    stayRepository.save(fabricStayService.createStay(stayDto));
                    System.out.println("\n\nSalida registrada exitosamente.\n\n");
                    if (stayDto.getVehicleDTO().getTypeVehicleDTO().getName()
                            .equals("NoResident")) {
                        stayDto.setPayment(ChronoUnit.MINUTES.between(stayDto.getTimeEntry(),
                                stayDto.getTimeDeparture()) * Constants.priceNoResident);
                        stayDto.setTimeTotalMin(ChronoUnit.MINUTES.between(stayDto.getTimeEntry(),
                                stayDto.getTimeDeparture()));
                        System.out
                                .println("Num. Placa:\t" + stayDto.getVehicleDTO().getNumberPlate()
                                        + "\tTiempo Estacionado (min):\t"
                                        + stayDto.getTimeTotalMin() + "\tCantidad a Pagar:\t"
                                        + stayDto.getPayment() * Constants.priceNoResident);
                        System.out.println("\n");
                    }
                    return Optional.of(stayDto);
                } else {
                    System.out.println(
                            "Ya se encuentra registrada una Salida no se puede volver a ingresar.");
                    return Optional.empty();
                }

            } else {
                System.out.println(
                        "No se encontro registro de entrada al estacionamiento del auto, favor de ingresar una entrada");
                return Optional.empty();
            }

        } else {
            System.out.println("No se encontro Vehiculo registrado");
            return Optional.empty();
        }
    }

    public StayDTO findByVehicle(VehicleDTO vehicleDTO) {
        List<StayDTO> staysDto = fabricStayService.createStaysDto(stayRepository.findAll());
        Optional<StayDTO> stayDto = staysDto.stream().filter(
                singleStayDto -> singleStayDto.getVehicleDTO().getId().equals(vehicleDTO.getId()))
                .findFirst();

        return stayDto.orElse(null);
    }


    public String startMonth() {
        List<StayDTO> staysDto = fabricStayService.createStaysDto(stayRepository.findAll());
        for (StayDTO stayDto : staysDto) {
            if (stayDto.getVehicleDTO().getTypeVehicleDTO().getName().equals("Official")) {
                stayRepository.delete(fabricStayService.createStay(stayDto));
            } else if (stayDto.getVehicleDTO().getTypeVehicleDTO().getName().equals("Resident")) {
                stayDto.setTimeEntry(LocalDateTime.now());
                stayDto.setTimeDeparture(null);
                stayRepository.save(fabricStayService.createStay(stayDto));
            }
        }

        System.out.println(
                "Se eliminan todas las instancias de autos Oficiales y se pone en 0 los autos estacionados de tipo Residente");
        return "Se eliminan todas las instancias de autos Oficiales y se pone en 0 los autos estacionados de tipo Residente";
    }


    public File generateResidentPayment(String nameFile) {
        List<StayDTO> staysDto = fabricStayService.createStaysDto(stayRepository.findAll());
        File file = new File(nameFile + ".txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Informe de Pagos de Residentes:\n\n");
            writer.write("Num. Placa:\t\tTiempo Estacionado (min):\t\tCantidad a Pagar:\n\n");
            for (StayDTO stayDto : staysDto) {
                if (stayDto.getVehicleDTO().getTypeVehicleDTO().getName().equals("Resident")) {
                    writer.write("\t" + stayDto.getVehicleDTO().getNumberPlate() + "\t\t\t\t\t");
                    writer.write(ChronoUnit.MINUTES.between(stayDto.getTimeEntry(),
                            stayDto.getTimeDeparture() != null ? stayDto.getTimeDeparture()
                                    : LocalDateTime.now())
                            + "\t\t\t\t\t\t\t\t\t");
                    writer.write(
                            ChronoUnit.MINUTES.between(stayDto.getTimeEntry(),
                                    stayDto.getTimeDeparture() != null ? stayDto.getTimeDeparture()
                                            : LocalDateTime.now())
                                    * Constants.priceResident + "\n");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return file;
    }



}
