package com.example.restaurante.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.restaurante.model.Cliente;


public interface ClienteService {

	
	public List<Cliente> retornarClientes();
	
	Cliente agregarCliente(Cliente cliente);
	
	public void eliminarCliente(Long id);
	
	Cliente actualizarCliente(Long id, Cliente cliente);
	
	public Cliente obtenerClienteById(Long id);
	
	
}
