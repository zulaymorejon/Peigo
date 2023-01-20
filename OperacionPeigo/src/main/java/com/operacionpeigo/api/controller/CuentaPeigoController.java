package com.operacionpeigo.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.operacionpeigo.api.models.entity.CuentaPeigo;
import com.operacionpeigo.api.models.entity.MensajeEntradaOperacion;
import com.operacionpeigo.api.models.entity.MensajeRespuestaOperacion;
import com.operacionpeigo.api.models.entity.OperacionPeigo;
import com.operacionpeigo.api.models.service.ICuentaPeigoService;

@RestController
@RequestMapping("/api")
public class CuentaPeigoController {

	public static final Logger logger = LoggerFactory.getLogger(CuentaPeigoController.class);
	
	@Autowired
	@Qualifier("ICuentaPeigoService")
	private ICuentaPeigoService icuentaPeigoService;
	
	@Value("${service.url}")
	private String url;
	
	@GetMapping(path = "/buscarCuentas", 
			produces = { MediaType.APPLICATION_JSON_VALUE,
						 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CuentaPeigo>> buscarComentariosConExchange() {

		logger.info("Ingresa a metodo buscarCuentas");
		
		return icuentaPeigoService.buscarCuentasConExchange();
	}

	@GetMapping(path = "/buscarPorCuenta/{numeroCuenta}")
	public ResponseEntity<?> consultarNumeroCuenta(@PathVariable("numeroCuenta") String numeroCuenta){
		//RestTemplate restTemplate = new RestTemplate();
		
		//Object cuenta = restTemplate.getForObject(url+"/"+numeroCuenta, Object.class);
		
		Map<String, Object> response = new HashMap<>();
		CuentaPeigo cuenta2 = new CuentaPeigo();
		
		CuentaPeigo cuenta = icuentaPeigoService.consultarNumeroCuenta(numeroCuenta);
		
		response.put("mensaje", "Consulta realizada con exito.");
		response.put("cuenta", cuenta);
		
		logger.info("prueba cast: ", cuenta);
		//logger.info("prueba cast2: ", cuenta.getNumeroCuenta().toString());
		//return cuenta;
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping(path = "/actualizarCuenta/{numeroCuenta}")
	public void updateEmployee(@PathVariable("numeroCuenta") String numeroCuenta) {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("numeroCuenta", numeroCuenta);
        CuentaPeigo updatedEmployee = new CuentaPeigo(numeroCuenta, "NuevoNombre", "aho", 20.0, "Activo");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url+"/"+numeroCuenta, updatedEmployee, params);
    }
	
	@PostMapping(path = "/operacion")
	public MensajeRespuestaOperacion operacion(@Valid @RequestBody MensajeEntradaOperacion operacion){
		MensajeRespuestaOperacion transaccion = new MensajeRespuestaOperacion();
		transaccion = icuentaPeigoService.operacion(operacion);
		logger.info("prueba operacion de la consulta"+ operacion.getCuentaDestino().toString());
		return transaccion;
	}

}
