package com.example.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurante.model.Reserva;
import com.example.restaurante.repository.ReservaRepository;
import com.example.restaurante.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository repoReserva;
	
	public void ReservaService(ReservaRepository repoReserva) {
		this.repoReserva = repoReserva;
	}
	
	@Override
	public List<Reserva> retornarReservas(){
		return repoReserva.findAll();
	}
	
	@Override
	public Reserva agregarReserva(Reserva reserva) {
		return repoReserva.save(reserva);
	}
	
	@Override
	public void eliminarReserva(Long id) {
		repoReserva.deleteById(id);
	}
	
	@Override
	public Reserva actualizarReserva(Long id, Reserva reserva) {
		Reserva reservaModificada = repoReserva.findById(id).get();
		reservaModificada.setFechaHora(reserva.getFechaHora());
		reservaModificada.setMesa(reserva.getMesa());
		reservaModificada.setNumeroPersonas(reserva.getNumeroPersonas());
		
		repoReserva.save(reservaModificada);
		return reservaModificada;
	}
	
	@Override
	public Reserva obtenerReservaById(Long id) {
		return repoReserva.findById(id).get();
	}

}
