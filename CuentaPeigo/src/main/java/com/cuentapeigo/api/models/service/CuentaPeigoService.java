package com.cuentapeigo.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentapeigo.api.models.entity.CuentaPeigo;
import com.cuentapeigo.api.models.repository.ICuentaPeigoRepository;

@Service
public class CuentaPeigoService implements ICuentaPeigoService{
	
	@Autowired
	private ICuentaPeigoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CuentaPeigo> findAll(){
		return (List<CuentaPeigo>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public CuentaPeigo findById(String numeroCuenta) {
		return repository.findById(numeroCuenta).orElse(null);
	}

	@Override
	@Transactional
	public CuentaPeigo save(CuentaPeigo cuentaPeigo) {
		return repository.save(cuentaPeigo);
	}
	
	/*@Override
	@Transactional
	public CuentaPeigo findByCuenta(String cuenta) {
		return repository.findByNumeroCuenta(cuenta);
	}*/

	@Override
	@Transactional
	public void deleteById(String numeroCuenta) {
		repository.deleteById(numeroCuenta);
	}

}
