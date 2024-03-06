package com.getechnologiesMx.parking.model;

import java.time.LocalDateTime;
import com.getechnologiesMx.parking.dto.StayDTO;
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
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stay_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime timeEntry;
    private LocalDateTime timeDeparture;



    public Stay(StayDTO stayDTO) {
        this.id = stayDTO.getId();
        this.timeEntry = stayDTO.getTimeEntry();
        this.timeDeparture = stayDTO.getTimeDeparture();
        this.vehicle = new Vehicle(stayDTO.getVehicleDTO());

    }

}
