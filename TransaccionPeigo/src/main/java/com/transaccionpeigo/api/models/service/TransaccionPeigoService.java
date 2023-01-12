package com.transaccionpeigo.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;
import com.transaccionpeigo.api.models.repository.ITransaccionPeigoRepository;

@Service
public class TransaccionPeigoService implements ITransaccionPeigoService{

	@Autowired
	private ITransaccionPeigoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<TransaccionPeigo> findAll(){
		return (List<TransaccionPeigo>) repository.findAll();
	}
	
	@Override
	@Transactional
	public TransaccionPeigo save(TransaccionPeigo transaccionPeigo) {
		return repository.save(transaccionPeigo);
	}
}
