package com.getechnologiesMx.parking.controller;

import org.springframework.web.bind.annotation.RestController;
import com.getechnologiesMx.parking.dto.StayDTO;
import com.getechnologiesMx.parking.dto.VehicleDTO;
import com.getechnologiesMx.parking.service.StayService;
import com.getechnologiesMx.parking.service.VehicleService;
import java.io.File;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.io.FileUtils;



@RestController
@RequestMapping("api/v1/parking")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private StayService stayService;

    @PostMapping("/entry")
    public ResponseEntity<StayDTO> registerEntry(@RequestBody VehicleDTO vehicleDTO) {
        try {
            StayDTO stayDTO = stayService.registerEntry(vehicleDTO).orElseThrow();
            return ResponseEntity.ok(stayDTO);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el Optional está vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/departure")
    public ResponseEntity<StayDTO> registerDeparture(@RequestBody VehicleDTO vehicleDTO) {
        try {
            StayDTO stayDTO = stayService.registerDeparture(vehicleDTO).orElseThrow();
            return ResponseEntity.ok(stayDTO);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el Optional está vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/official/save")
    public ResponseEntity<VehicleDTO> saveOffical(@RequestBody VehicleDTO vehicleDTO) {

        try {
            VehicleDTO vehicleDto =
                    vehicleService.oficcialRegisterVehicle(vehicleDTO).orElseThrow();
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el Optional está vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/resident/save")
    public ResponseEntity<VehicleDTO> saveResident(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO vehicleDto =
                    vehicleService.residentRegisterVehicle(vehicleDTO).orElseThrow();
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el Optional está vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/noresident/save")
    public ResponseEntity<VehicleDTO> saveNoResident(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO vehicleDto =
                    vehicleService.noResidentRegisterVehicle(vehicleDTO).orElseThrow();
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el Optional está vacío
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/startmonth")
    public ResponseEntity<String> iniciarNuevoMes() {
        return ResponseEntity.ok().body(stayService.startMonth());
    }

    @PostMapping("/paymentresident")
    public ResponseEntity<Resource> generarInformePagosResidentes(
            @RequestBody Map<String, String> requestBody) {
        String nameFile = requestBody.get("nameFile");
        try {
            File file = stayService.generateResidentPayment(nameFile);
            ByteArrayResource resource = new ByteArrayResource(FileUtils.readFileToByteArray(file));
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getName() + "\"").body(resource);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }


    }

}
