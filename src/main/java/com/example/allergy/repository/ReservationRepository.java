package com.example.allergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.allergy.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
