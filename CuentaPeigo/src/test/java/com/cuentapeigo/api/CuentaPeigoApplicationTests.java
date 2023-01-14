package com.cuentapeigo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cuentapeigo.api.models.entity.CuentaPeigo;
import com.cuentapeigo.api.models.repository.ICuentaPeigoRepository;
import com.cuentapeigo.api.models.service.CuentaPeigoService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CuentaPeigoApplicationTests.class})
@SpringBootTest
class CuentaPeigoApplicationTests {

	@InjectMocks
	private CuentaPeigoService service;
		
	@Mock
	private ICuentaPeigoRepository respository;
	
	@Test
	void findAllTotalRegistrosTest() {
		when(respository.findAll()).thenReturn(DatosPrueba.cuentas);
		
		List<CuentaPeigo> cuenta = service.findAll();
		assertEquals(2, cuenta.size());
		assertNotNull(cuenta);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void findAllVacioTest() {		
		List<CuentaPeigo> cuenta = Collections.emptyList();
		when(respository.findAll()).thenReturn(cuenta);
		List<CuentaPeigo> cuentaVacio = service.findAll();
		assertEquals(0, cuentaVacio.size());
		assertFalse(cuentaVacio==null);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void saveTest() {		
		
		  when(respository.save(any(CuentaPeigo.class))).thenReturn(DatosPrueba.
		  cuentaResponse); CuentaPeigo cuenta = service.save(DatosPrueba.cuentaRequest());
		  assertEquals("Zulay", cuenta.getCliente()); assertEquals("123456",
		  cuenta.getNumeroCuenta());
		  verify(respository,times(1)).save(any(CuentaPeigo.class));
		 
	}
	
	/*
	@Test
	void deleteTest() {		
		respository.findById(anyLong());
		service.deleteById(1L);

		verify(respository,times(1)).deleteById(anyLong());
	}*/

}
