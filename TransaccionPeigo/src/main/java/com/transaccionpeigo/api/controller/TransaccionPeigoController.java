package com.transaccionpeigo.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaccionpeigo.api.models.entity.TransaccionPeigo;
import com.transaccionpeigo.api.models.service.ITransaccionPeigoService;

@RestController
@RequestMapping("/api")
public class TransaccionPeigoController {

	@Autowired
	private ITransaccionPeigoService service;
	
	@GetMapping("/transacciones")
	public List<TransaccionPeigo> listarTransaccion(){
		return service.findAll();
	}
	
	@PostMapping("/transacciones")
	public ResponseEntity<?> guardarCuenta(@Valid @RequestBody TransaccionPeigo transacciones, BindingResult result){
		TransaccionPeigo transaccionesObj = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("error", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			transaccionesObj = service.save(transacciones);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Transaccion creada con exito.");
		response.put("transaccion", transaccionesObj);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}
