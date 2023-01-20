package com.cuentapeigo.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentapeigo.api.models.entity.CuentaPeigo;
import com.cuentapeigo.api.models.entity.MensajeRespuesta;
import com.cuentapeigo.api.models.service.ICuentaPeigoService;

@RestController
@RequestMapping("/api")
public class CuentaPeigoController {
	
	public static final Logger logger = LoggerFactory.getLogger(CuentaPeigoController.class);
	
	@Autowired
	private ICuentaPeigoService service;
	
	@GetMapping("/cuentas")
	public List<CuentaPeigo> listarCuenta(){
		return service.findAll();
	}
	
	@GetMapping("/cuentas/{numeroCuenta}")
	public CuentaPeigo consultarNumeroCuenta(@PathVariable("numeroCuenta") String numeroCuenta){
		Map<String, Object> response = new HashMap<>();
		CuentaPeigo cuentaObj = service.findById(numeroCuenta);
		
		if(cuentaObj == null) {
			response.put("mensaje", "No existe la cuenta con el numero " + numeroCuenta);
			return null;
		}
		
		response.put("mensaje", "Consulta realizada con exito.");
		response.put("cuenta", cuentaObj);
		
		return cuentaObj;
	}
	
	@PostMapping("/cuentas")
	public ResponseEntity<?> guardarCuenta(@Valid @RequestBody CuentaPeigo cuentas, BindingResult result){
		CuentaPeigo cuentasObj = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("error", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			cuentasObj = service.save(cuentas);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cuentas creada con exito.");
		response.put("cuenta", cuentasObj);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/cuentas/{numeroCuenta}")
	public MensajeRespuesta actualizarCuenta2(@Valid @RequestBody CuentaPeigo cuenta, BindingResult result, @PathVariable String numeroCuenta){
		CuentaPeigo cuentaFinal = null;
		MensajeRespuesta respuesta = new MensajeRespuesta();
		
		CuentaPeigo cuentaObj = service.findById(numeroCuenta);
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			respuesta.setMensaje("erros : "+ resultError);
			return respuesta;
		}
		
		if(cuentaObj == null) {
			respuesta.setMensaje("No existe la cuenta con el numero " + numeroCuenta);
			return respuesta;
		}
		
		try {
			
			cuentaObj.setNumeroCuenta(cuenta.getNumeroCuenta());
			cuentaObj.setCliente(cuenta.getCliente());
			cuentaObj.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaObj.setSaldo(cuenta.getSaldo());
			cuentaObj.setEstado(cuenta.getEstado());
				
			cuentaFinal = service.save(cuentaObj);
			
		} catch (DataAccessException e) {
			respuesta.setMensaje("Se produjo un error en la aplicacion");
			return respuesta;
		}
		
		respuesta.setMensaje("Cliente actualizado con exito.");
		respuesta.setCuenta(cuentaFinal);
		
		return respuesta;
	}
	
	@DeleteMapping("/cuentas/{numeroCuenta}")
	public ResponseEntity<?> eliminar(@PathVariable String numeroCuenta){
		Map<String, Object> response = new HashMap<>();
		try {
			service.deleteById(numeroCuenta);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cuenta eliminada con exito.");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}

}
