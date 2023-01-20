package com.transaccionpeigo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaccionpeigo.api.models.entity.MensajeRespuesta;
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
	public MensajeRespuesta guardarTransaccion(@Valid @RequestBody TransaccionPeigo transacciones, BindingResult result){
		TransaccionPeigo transaccionesObj = null;
		MensajeRespuesta respuesta = new MensajeRespuesta();
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());

			respuesta.setMensaje("error : " + resultError);
			return respuesta;
		}
		
		try {
			transaccionesObj = service.save(transacciones);
		} catch (DataAccessException e) {
			respuesta.setMensaje("Se produjo un error en la aplicacion");
			return respuesta;
		}
		
		respuesta.setMensaje("Transaccion creada con exito.");
		respuesta.setTransaccion(transaccionesObj);
		
		return respuesta;
	}
	
}
