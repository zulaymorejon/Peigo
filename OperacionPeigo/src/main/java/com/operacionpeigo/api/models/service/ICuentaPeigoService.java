package com.operacionpeigo.api.models.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.operacionpeigo.api.models.entity.CuentaPeigo;

public interface ICuentaPeigoService {

	ResponseEntity<List<CuentaPeigo>> buscarCuentasConExchange();
	ResponseEntity<CuentaPeigo> buscarPorCuenta(String numeroCuenta);
}
