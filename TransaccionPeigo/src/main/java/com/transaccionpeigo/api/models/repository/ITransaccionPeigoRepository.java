package com.transaccionpeigo.api.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;


public interface ITransaccionPeigoRepository extends PagingAndSortingRepository<TransaccionPeigo, String> {

}
