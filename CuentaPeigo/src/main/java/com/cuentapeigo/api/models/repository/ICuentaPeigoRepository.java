package com.cuentapeigo.api.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuentapeigo.api.models.entity.CuentaPeigo;

public interface ICuentaPeigoRepository extends PagingAndSortingRepository<CuentaPeigo, String>{

	/*@Query("select p.* cuenta c where c.numcuenta = :numcuenta")
	CuentaPeigo findByNumeroCuenta(@Param("numcuenta") String numeroCuenta);*/
}