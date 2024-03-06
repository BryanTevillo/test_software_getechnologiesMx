package com.getechnologiesMx.parking.dto;

import java.time.LocalDateTime;
import com.getechnologiesMx.parking.model.Stay;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StayDTO {
    private Integer id;
    private LocalDateTime timeEntry;
    private LocalDateTime timeDeparture;
    private VehicleDTO vehicleDTO;
    private Long timeTotalMin;
    private Double payment;

    public StayDTO(Stay stay) {
        this.id = stay.getId();
        this.timeEntry = stay.getTimeEntry();
        this.timeDeparture = stay.getTimeDeparture();
        this.vehicleDTO = new VehicleDTO(stay.getVehicle());
    }


}
