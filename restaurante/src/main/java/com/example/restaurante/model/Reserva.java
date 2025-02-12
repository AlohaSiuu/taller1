package com.example.restaurante.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

/*
 * Entidad/Tabla para la base de datos
 */
@Entity
public class Reserva {

	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20)
	private LocalDateTime fechaHora;
	

	@Column(length = 20)
	private int NumeroPersonas;
	
    // Relación muchos a uno con Mesa
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    // Relación muchos a uno con Cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "cliente_id")  
    private Cliente cliente;

	
	//Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}


	public int getNumeroPersonas() {
		return NumeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		NumeroPersonas = numeroPersonas;
	}
	
	
	
	
}