package com.transaccionpeigo.api;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;

public class DatosPrueba {
	static Date fecha = new Date();
	
	public static final List<TransaccionPeigo> transacciones = Arrays.asList(new TransaccionPeigo(1L, "123456", "654123", 500.00, fecha, "zmorejon"),
			new TransaccionPeigo(2L, "123457", "654129", 150.00, fecha, "zmorejon"));
	
	

}
