package com.example.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurante.model.Cliente;
import com.example.restaurante.repository.ClienteRepository;
import com.example.restaurante.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repoCliente;
    
    @Override
    public List<Cliente> retornarClientes() {
        return repoCliente.findAll();
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return repoCliente.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        repoCliente.deleteById(id);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteModificado = repoCliente.findById(id).get();
        clienteModificado.setEmail(cliente.getEmail());
        clienteModificado.setNombre(cliente.getNombre());
        clienteModificado.setTelefono(cliente.getTelefono());
        clienteModificado.setPassword(cliente.getPassword());
        
        repoCliente.save(clienteModificado);
        return clienteModificado;
    }

    @Override
    public Cliente obtenerClienteById(Long id) {
        return repoCliente.findById(id).get();
    }
}

