package com.transaccionpeigo.api.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="transaccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TransaccionPeigo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperacion;
	
	@Pattern(regexp = "[0-9]+",message = "Solo permite valores numericos")
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(unique=true, name="cuentaOrigen")
	private String cuentaOrigen;
	
	@Pattern(regexp = "[0-9]+",message = "Solo permite valores numericos")
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(unique=true, name="cuentaDestino")
	private String cuentaDestino;
	
	@Column(name = "monto")
	private Double monto;
	
	@Column(name = "fechaTransaccion")
	private Date fechaTransaccion;
	
	@Column(name = "usuarioTransaccion")
	private String usuarioTransaccion;
	
	private static final long serialVersionUID = 4334455564095195932L;
	
}
