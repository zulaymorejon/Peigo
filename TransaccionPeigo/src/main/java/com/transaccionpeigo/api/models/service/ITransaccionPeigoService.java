package com.transaccionpeigo.api.models.service;

import java.util.List;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;

public interface ITransaccionPeigoService {
	
	List<TransaccionPeigo> findAll();
	TransaccionPeigo save(TransaccionPeigo transaccionPeigo);

}
