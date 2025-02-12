package com.example.restaurante.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

/*
 * Entidad/Tabla de la base de datos
 */
@Entity
public class Mesa {

	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10, nullable = false)
	private int numeroDeMesa;
	
	private boolean disponible;
	
	@OneToMany(mappedBy = "mesa")
	@JsonBackReference
	private List<Reserva> reservas;

	
	//Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroDeMesa() {
		return numeroDeMesa;
	}

	public void setNumeroDeMesa(int numeroDeMesa) {
		this.numeroDeMesa = numeroDeMesa;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}

