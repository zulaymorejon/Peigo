package com.operacionpeigo.api.models.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaPeigo implements Serializable{

	private static final long serialVersionUID = 4334455564095195932L;
	
	@JsonProperty("numeroCuenta")	
	private String numeroCuenta;
	@JsonProperty("cliente")
	private String cliente;
	@JsonProperty("tipoCuenta")
	private String tipoCuenta;
	@JsonProperty("saldo")
	private Double saldo;
	@JsonProperty("estado")
	private String estado;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaPeigo [numeroCuenta=");
		builder.append(numeroCuenta);
		builder.append(", cliente=");
		builder.append(cliente);
		builder.append(", tipoCuenta=");
		builder.append(tipoCuenta);
		builder.append(", saldo=");
		builder.append(saldo);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}

}
