package com.operacionpeigo.api.models.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.operacionpeigo.api.models.entity.CuentaPeigo;
import com.operacionpeigo.api.models.entity.MensajeEntradaCuenta;
import com.operacionpeigo.api.models.entity.MensajeEntradaOperacion;
import com.operacionpeigo.api.models.entity.MensajeRespuestaOperacion;

public interface ICuentaPeigoService {

	ResponseEntity<List<CuentaPeigo>> buscarCuentasConExchange();
	CuentaPeigo consultarNumeroCuenta(String numeroCuenta);
	MensajeEntradaCuenta actualizaCuenta(CuentaPeigo cuenta, Double monto);
	MensajeRespuestaOperacion operacion(MensajeEntradaOperacion operacion);
}
