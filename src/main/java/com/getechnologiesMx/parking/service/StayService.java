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

    public void registerEntry(StayDTO stayDTO) {
        // Lógica para registrar la entrada de un vehículo
        stayDTO.setTimeEntry(LocalDateTime.now());
        stayRepository.save(fabricStayService.createStay(stayDTO));
    }

    public StayDTO findByVehicle(VehicleDTO vehicleDTO) {
        List<StayDTO> staysDto = fabricStayService.createStaysDto(stayRepository.findAll());
        Optional<StayDTO> stayDto = staysDto.stream().filter(
                singleStayDto -> singleStayDto.getVehicleDTO().getId().equals(vehicleDTO.getId()))
                .findFirst();

        return stayDto.orElse(null);
    }

    public void registerDeparture(StayDTO stayDTO) {
        stayDTO.setTimeDeparture(LocalDateTime.now());
        stayRepository.save(fabricStayService.createStay(stayDTO));
    }

    public void iniciarNuevoMes() {
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

    }


    public File generarInformePagosResidentes(String nameFile) {
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
