package com.operacionpeigo.api.models.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.operacionpeigo.api.models.entity.CuentaPeigo;

@Service("ICuentaPeigoService")
public class CuentaPeigoService implements ICuentaPeigoService{

	public static final Logger logger = LoggerFactory.getLogger(CuentaPeigoService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${service.url}")
	private String url;
	
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

		logger.info("Response: {}", response.getBody());

		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CuentaPeigo> buscarPorCuenta(String numeroCuenta) {

		logger.info("Inicia Busqueda");

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<CuentaPeigo> entity = new HttpEntity<>(headers);

		ResponseEntity<CuentaPeigo> response = restTemplate.exchange(
				url+"/"+numeroCuenta, HttpMethod.GET, entity,
				new ParameterizedTypeReference<CuentaPeigo>() {
				});

		logger.info("Response: {}", response.getBody());

		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

}
