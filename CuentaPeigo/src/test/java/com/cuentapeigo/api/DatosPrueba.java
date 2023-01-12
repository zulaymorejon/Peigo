package com.cuentapeigo.api;

import java.util.Arrays;
import java.util.List;

import com.cuentapeigo.api.models.entity.CuentaPeigo;

public class DatosPrueba {

	public static final List<CuentaPeigo> cuentas = Arrays.asList(new CuentaPeigo(1L, "123456", "Zulay", "Ahorro", 500.00, "Activo"),
			new CuentaPeigo(2L, "123457", "Edith", "Ahorro", 150.00, "Activo"));
	
	public static final CuentaPeigo cuentaResponse= new CuentaPeigo(1L, "123456", "Zulay", "Ahorro", 500.00, "Activo");
	
	public static CuentaPeigo cuentaRequest() {
		CuentaPeigo cuenta = new CuentaPeigo();
		return cuenta.builder()
		.numeroCuenta("123456")
		.cliente("0929008316")
		.tipoCuenta("Ahorro")
		.saldo(500.00)
		.estado("Activo")
		.build();
		
	}
}
