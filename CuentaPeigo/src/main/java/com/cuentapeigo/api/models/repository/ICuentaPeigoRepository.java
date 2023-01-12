package com.cuentapeigo.api.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuentapeigo.api.models.entity.CuentaPeigo;

public interface ICuentaPeigoRepository extends PagingAndSortingRepository<CuentaPeigo, Long>{

}
