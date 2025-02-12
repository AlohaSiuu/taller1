package com.example.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurante.model.Reserva;
import com.example.restaurante.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping
	public List<Reserva> getAllReservas(){
		List<Reserva> reservas = reservaService.retornarReservas();
		return reservas;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
		Reserva reserva = reservaService.obtenerReservaById(id);
		
		if(reserva == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(reserva);
	}
	
	@PostMapping("/add")
	public Reserva addReserva(@RequestBody Reserva reserva){
		return reservaService.agregarReserva(reserva);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id){
		reservaService.eliminarReserva(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva, @PathVariable Long id){
		Reserva reservaExistente = reservaService.obtenerReservaById(id);
		
		if(reservaExistente != null) {
			Reserva reservaModificada = reservaExistente;
			reservaModificada.setFechaHora(reserva.getFechaHora());
			reservaModificada.setMesa(reserva.getMesa());
			reservaModificada.setNumeroPersonas(reserva.getNumeroPersonas());
			
			reservaService.actualizarReserva(id, reservaModificada);
			return ResponseEntity.ok(reservaModificada);
		}
		
		return ResponseEntity.notFound().build();
	}
}
