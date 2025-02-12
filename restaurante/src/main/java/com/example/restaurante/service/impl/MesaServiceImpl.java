package com.example.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurante.model.Mesa;
import com.example.restaurante.repository.MesaRepository;
import com.example.restaurante.service.MesaService;

@Service
public class MesaServiceImpl implements MesaService{

	@Autowired
	private MesaRepository repoMesa;
	
	public void MesaService(MesaRepository repoMesa) {
		this.repoMesa = repoMesa;
	}
	
	@Override
	public List<Mesa> retornarMesas(){
		return repoMesa.findAll();
	}
	
	@Override
	public Mesa agregarMesa(Mesa mesa) {
		return repoMesa.save(mesa);
	}
	
	@Override
	public void eliminarMesa(Long id) {
		repoMesa.deleteById(id);
	}
	
	@Override
	public Mesa actualizarMesa(Long id, Mesa mesa) {
		Mesa mesaModificada = repoMesa.findById(id).get();
		mesaModificada.setNumeroDeMesa(mesa.getNumeroDeMesa());
		mesaModificada.setReservas(mesa.getReservas());
		mesaModificada.setDisponible(mesa.getDisponible());
		
		repoMesa.save(mesaModificada);
		return mesaModificada;
	}
	
	@Override
	public Mesa obtenerMesaById(Long id) {
		return repoMesa.findById(id).get();
	}

}
