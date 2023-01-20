package com.operacionpeigo.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRespuestaOperacion {

	private String mensaje;
	private OperacionPeigo operacion;	
	
}
