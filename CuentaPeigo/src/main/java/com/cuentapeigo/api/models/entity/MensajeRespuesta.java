package com.cuentapeigo.api.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRespuesta {
	
	private String mensaje;
	private CuentaPeigo cuenta;
	
	
	

}
