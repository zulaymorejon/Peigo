package com.operacionpeigo.api.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacionPeigo {
	

	
	private String cuentaOrigen;
	private String cuentaDestino;
	private Double montoCtaOrigen;
	private Double montoCtaDestino;
	private Long idTransaccion;
}
