package com.example.restaurante.service;

import java.util.List;

import com.example.restaurante.model.Mesa;



public interface MesaService {
	
	
	public List<Mesa> retornarMesas();
	
	Mesa agregarMesa(Mesa cliente);
	
	public void eliminarMesa(Long id);
	
	Mesa actualizarMesa(Long id, Mesa mesa);
	
	public Mesa obtenerMesaById(Long id);

}
