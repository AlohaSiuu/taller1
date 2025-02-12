package com.example.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurante.model.Cliente;
import com.example.restaurante.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
	public List<Cliente> getAllClientes(){
		List<Cliente> clientes = clienteService.retornarClientes();
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		Cliente cliente = clienteService.obtenerClienteById(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	 @PostMapping("/add")
	    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
	        try {
	            // Intentamos agregar el cliente
	            Cliente clienteRegistrado = clienteService.agregarCliente(cliente);
	            return ResponseEntity.status(HttpStatus.CREATED).body(clienteRegistrado);
	        } catch (DataIntegrityViolationException e) {
	            // Si hay una violación de integridad (como un correo duplicado)
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                                 .body("El correo electrónico ya está registrado.");
	        } catch (Exception e) {
	            // En caso de otros errores
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Hubo un error al registrar el cliente.");
	        }
	    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
		clienteService.eliminarCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		 Cliente clienteExistente = clienteService.obtenerClienteById(id);
	        if(clienteExistente != null) {
	            Cliente clienteModificado = clienteExistente;
	            clienteModificado.setNombre(cliente.getNombre());
	            clienteModificado.setEmail(cliente.getEmail());
	            clienteModificado.setTelefono(cliente.getTelefono());
	            clienteModificado.setPassword(cliente.getPassword());
	            clienteModificado.setActivo(cliente.isActivo());
	            
	            clienteService.actualizarCliente(id, clienteModificado);
	            return ResponseEntity.ok(clienteModificado); 
	        }
	        return ResponseEntity.notFound().build(); 
	}

}
