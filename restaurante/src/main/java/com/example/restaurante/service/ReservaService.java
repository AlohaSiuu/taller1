package com.example.restaurante.service;

import java.util.List;

import com.example.restaurante.model.Reserva;


public interface ReservaService {

	
	public List<Reserva> retornarReservas();
	
	Reserva agregarReserva(Reserva reserva);
	
	public void eliminarReserva(Long id);
	
	Reserva actualizarReserva(Long id, Reserva reserva);
	
	public Reserva obtenerReservaById(Long id);

}
