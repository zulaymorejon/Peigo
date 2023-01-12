package com.operacionpeigo.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.operacionpeigo.api.models.entity.CuentaPeigo;

@Service
public class CuentaPeigoService {

	@Autowired
    RestTemplate restTemplate;
    public List<CuentaPeigo> getMovies(){
        ResponseEntity<CuentaPeigo[]> response = restTemplate.getForEntity("http://services-cuenta/api/cuentas/", CuentaPeigo[].class);
        		CuentaPeigo[] cuentas = response.getBody();
                List<CuentaPeigo> m = Arrays.asList(cuentas);
        return  m;
    }
    
}
