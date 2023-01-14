package com.cuentapeigo.api.models.service;

import java.util.List;

import com.cuentapeigo.api.models.entity.CuentaPeigo;

public interface ICuentaPeigoService {
	List<CuentaPeigo> findAll();
	CuentaPeigo save(CuentaPeigo cuentaPeigo);
	CuentaPeigo findById(String numCuenta);
	//CuentaPeigo findByCuenta(String cuenta);
	void deleteById(String numCuenta);
}
