package com.operacionpeigo.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.operacionpeigo.api.models.entity.CuentaPeigo;
import com.operacionpeigo.api.models.entity.OperacionPeigo;
import com.operacionpeigo.api.models.service.CuentaPeigoService;
import com.operacionpeigo.api.models.service.ICuentaPeigoService;

@RestController
public class CuentaPeigoController {

	public static final Logger logger = LoggerFactory.getLogger(CuentaPeigoController.class);
	
	@Autowired
	@Qualifier("ICuentaPeigoService")
	private ICuentaPeigoService icuentaPeigoService;
	
	@GetMapping(path = "/buscarCuentas", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
						 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CuentaPeigo>> buscarComentariosConExchange() {

		logger.info("Ingresa a metodo buscarCuentas");
		
		return icuentaPeigoService.buscarCuentasConExchange();
	}
	
	@GetMapping(path = "/buscar", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
						 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CuentaPeigo> buscarCuenta() {

		logger.info("Ingresa a metodo buscarCuentas");
		
		return icuentaPeigoService.buscarPorCuenta("123456");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping(path = "/operacion", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
						 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> realizarOperacion(@RequestBody OperacionPeigo Operacion, BindingResult result) {

		logger.info("Ingresa a metodo operacion");
		
		return null;
	}

}
