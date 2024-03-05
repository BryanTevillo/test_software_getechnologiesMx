package com.getechnologiesMx.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.getechnologiesMx.parking.model.Stay;

@Repository
public interface StayRepository extends JpaRepository<Stay, Integer> {
}
