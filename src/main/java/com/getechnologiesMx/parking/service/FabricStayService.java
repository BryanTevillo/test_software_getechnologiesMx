package com.getechnologiesMx.parking.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.getechnologiesMx.parking.dto.StayDTO;

import com.getechnologiesMx.parking.model.Stay;


@Service
public class FabricStayService {
    public Stay createStay(StayDTO stayDTO) {
        return new Stay(stayDTO);
    }

    public StayDTO createStayDto(Stay stay) {
        return new StayDTO(stay);
    }

    public List<StayDTO> createStaysDto(List<Stay> stays) {
        List<StayDTO> staysDto = new ArrayList<>();
        stays.forEach(stay -> {
            staysDto.add(new StayDTO(stay));
        });

        return staysDto;
    }

}
