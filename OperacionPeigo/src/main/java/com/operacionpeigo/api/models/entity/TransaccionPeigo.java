package com.operacionpeigo.api.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionPeigo implements Serializable {
	
	private static final long serialVersionUID = 4334455564095195932L;

	@JsonProperty("idOperacion")
	private Long idOperacion;
	private String cuentaOrigen;
	private String cuentaDestino;
	private Double monto;
	private Date fechaTransaccion;
	private String usuarioTransaccion;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransaccionPeigo [idOperacion=");
		builder.append(idOperacion);
		builder.append(", cuentaOrigen=");
		builder.append(cuentaOrigen);
		builder.append(", cuentaDestino=");
		builder.append(cuentaDestino);
		builder.append(", monto=");
		builder.append(monto);
		builder.append(", fechaTransaccion=");
		builder.append(fechaTransaccion);
		builder.append(", usuarioTransaccion=");
		builder.append(usuarioTransaccion);
		builder.append("]");
		return builder.toString();
	}
	
}
