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

import com.example.restaurante.model.Mesa;
import com.example.restaurante.service.MesaService;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

	@Autowired
	private MesaService mesaService;
	
	@GetMapping
	public List<Mesa> getAllMesas(){
		List<Mesa> mesas = mesaService.retornarMesas();
		return mesas;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Mesa> getMesaById(@PathVariable Long id) {
		Mesa mesa = mesaService.obtenerMesaById(id);
		
		if (mesa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mesa);
	}
	
	@PostMapping("/add")
	public Mesa addMesa(@RequestBody Mesa mesa) {
		return mesaService.agregarMesa(mesa);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
		mesaService.eliminarMesa(id); 
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Mesa> updateMesa(@RequestBody Mesa mesa, @PathVariable Long id){
		Mesa mesaExistente = mesaService.obtenerMesaById(id);
		if (mesaExistente != null) {
			Mesa mesaModificada = mesaExistente;
			mesaModificada.setDisponible(mesa.getDisponible());
			mesaModificada.setNumeroDeMesa(mesa.getNumeroDeMesa());
			mesaModificada.setReservas(mesa.getReservas());
			
			mesaService.actualizarMesa(id, mesaModificada);
			return ResponseEntity.ok(mesaModificada); 
		}
		
		return ResponseEntity.notFound().build();
	}
}
