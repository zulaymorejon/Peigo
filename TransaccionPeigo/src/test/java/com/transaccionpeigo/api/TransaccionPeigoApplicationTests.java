package com.transaccionpeigo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;
import com.transaccionpeigo.api.models.repository.ITransaccionPeigoRepository;
import com.transaccionpeigo.api.models.service.TransaccionPeigoService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {TransaccionPeigoApplicationTests.class})
@SpringBootTest
class TransaccionPeigoApplicationTests {

	@InjectMocks
	private TransaccionPeigoService service;
		
	@Mock
	private ITransaccionPeigoRepository respository;
	
	@Test
	void findAllTotalRegistrosTest() {
		when(respository.findAll()).thenReturn(DatosPrueba.transacciones);
		
		List<TransaccionPeigo> transaccion = service.findAll();
		assertEquals(2, transaccion.size());
		assertNotNull(transaccion);
		verify(respository,times(1)).findAll();
	}
	
}
