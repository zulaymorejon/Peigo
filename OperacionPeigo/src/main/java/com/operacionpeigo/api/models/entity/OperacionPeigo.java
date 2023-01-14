package com.operacionpeigo.api.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OperacionPeigo {
	
	@JsonProperty("numeroOperacion")	
	private String numeroOperacion;
	private String cuentaOrigen;
	private String cuentaDestino;
	private Double monto;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Operacion [numeroOperacion=");
		builder.append(numeroOperacion);
		builder.append(", cuentaOrigen=");
		builder.append(cuentaOrigen);
		builder.append(", cuentaDestino=");
		builder.append(cuentaDestino);
		builder.append(", monto=");
		builder.append(monto);
		builder.append("]");
		return builder.toString();
	}

}
