package com.operacionpeigo.api.models.service;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.operacionpeigo.api.models.entity.CuentaPeigo;
import com.operacionpeigo.api.models.entity.MensajeEntradaCuenta;
import com.operacionpeigo.api.models.entity.MensajeEntradaOperacion;
import com.operacionpeigo.api.models.entity.MensajeEntradaTransaccion;
import com.operacionpeigo.api.models.entity.MensajeRespuestaOperacion;
import com.operacionpeigo.api.models.entity.OperacionPeigo;
import com.operacionpeigo.api.models.entity.TransaccionPeigo;

@Service("ICuentaPeigoService")
public class CuentaPeigoService implements ICuentaPeigoService{

	public static final Logger logger = LoggerFactory.getLogger(CuentaPeigoService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${service.url}")
	private String url;
	
	@Value("${service.urlTransaccion}")
	private String urlTransaccion;
	
	@Override
	public ResponseEntity<List<CuentaPeigo>> buscarCuentasConExchange() {

		logger.info("Inicia Busqueda");

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<CuentaPeigo> entity = new HttpEntity<>(headers);

		ResponseEntity<List<CuentaPeigo>> response = restTemplate.exchange(
				url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<CuentaPeigo>>() {
				});

		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	public CuentaPeigo consultarNumeroCuenta(String numeroCuenta){
		RestTemplate restTemplate = new RestTemplate();
		CuentaPeigo cuenta = restTemplate.getForObject(url+"/"+numeroCuenta, CuentaPeigo.class);
		return cuenta;
	}
	
	public MensajeEntradaCuenta actualizaCuenta(CuentaPeigo cuenta, Double monto) {
        CuentaPeigo cuentaPeigo = new CuentaPeigo(cuenta.getNumeroCuenta(), cuenta.getCliente(), cuenta.getTipoCuenta(), monto, cuenta.getEstado());
        RestTemplate restTemplate = new RestTemplate();
		MensajeEntradaCuenta objCuenta = restTemplate.postForObject(url+"/"+cuenta.getNumeroCuenta(), cuentaPeigo,  MensajeEntradaCuenta.class);
		return objCuenta;
    }
	
	public MensajeEntradaTransaccion insertaTransaccion(TransaccionPeigo transaccion) {
        RestTemplate restTemplate = new RestTemplate();
        MensajeEntradaTransaccion objTransaccion = restTemplate.postForObject(urlTransaccion, transaccion,  MensajeEntradaTransaccion.class);
		return objTransaccion;
    }
	
	public MensajeRespuestaOperacion operacion(MensajeEntradaOperacion operacion) {
        CuentaPeigo cuentaOrigen = new CuentaPeigo();
        CuentaPeigo cuentaDestino = new CuentaPeigo();
        MensajeRespuestaOperacion operacionObj = new MensajeRespuestaOperacion();
        
        Double saldoOrigen;
        Double saldoDestino;
        
        cuentaOrigen = consultarNumeroCuenta(operacion.getCuentaOrigen());
        
        if (cuentaOrigen.getSaldo() > 0 && cuentaOrigen.getSaldo() > operacion.getMonto()) {
        	
        	saldoOrigen = cuentaOrigen.getSaldo() - operacion.getMonto();
        	
        	cuentaDestino = consultarNumeroCuenta(operacion.getCuentaDestino());
        	saldoDestino = cuentaDestino.getSaldo() + operacion.getMonto();
        	        	
        	MensajeEntradaCuenta objCtaOrigen = actualizaCuenta(cuentaOrigen,saldoOrigen);
        	MensajeEntradaCuenta objCtaDestino = actualizaCuenta(cuentaDestino,saldoDestino);
        	
        	TransaccionPeigo transaccion = new TransaccionPeigo();
        	transaccion.setCuentaDestino(objCtaDestino.getCuenta().getNumeroCuenta());
        	transaccion.setCuentaOrigen(objCtaOrigen.getCuenta().getNumeroCuenta());
        	transaccion.setFechaTransaccion(new Date());
        	transaccion.setMonto(saldoDestino);
        	transaccion.setUsuarioTransaccion("zmorejon");
        	
        	MensajeEntradaTransaccion objTransaccion = insertaTransaccion(transaccion);
        	
        	OperacionPeigo op = new OperacionPeigo();
        	op.setCuentaDestino(objCtaDestino.getCuenta().getNumeroCuenta());
        	op.setCuentaOrigen(objCtaOrigen.getCuenta().getNumeroCuenta());
        	op.setIdTransaccion(objTransaccion.getTransaccion().getIdOperacion());
        	op.setMontoCtaDestino(objCtaDestino.getCuenta().getSaldo());
        	op.setMontoCtaOrigen(objCtaOrigen.getCuenta().getSaldo());
        	operacionObj.setMensaje("Transaccion Exitosa");
        	operacionObj.setOperacion(op);
            
            return operacionObj;
        }
        else {
        	operacionObj.setMensaje("Fondos insuficientes para realizar la transaccion");
        	return operacionObj;
        }

    }

}
