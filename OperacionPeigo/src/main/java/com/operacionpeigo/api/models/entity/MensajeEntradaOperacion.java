package com.operacionpeigo.api.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensajeEntradaOperacion {

	@JsonProperty("numeroOperacion")	
	private String numeroOperacion;
	@JsonProperty("cuentaOrigen")
	private String cuentaOrigen;
	@JsonProperty("cuentaDestino")
	private String cuentaDestino;
	@JsonProperty("monto")
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
