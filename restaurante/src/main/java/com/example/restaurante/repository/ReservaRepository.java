package com.example.restaurante.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurante.model.Reserva;



public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}