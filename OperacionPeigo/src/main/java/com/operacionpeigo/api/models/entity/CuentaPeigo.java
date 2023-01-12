package com.operacionpeigo.api.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CuentaPeigo {

	@Id
	@GeneratedValue
	private Long id;
	private String numeroCuenta;
	private String cliente;
	private String tipoCuenta;
	private Double saldo;
	private String estado;
}
