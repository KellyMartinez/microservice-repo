package com.microservices.loja.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservices.loja.util.CompraStatus;

@Entity
@SequenceGenerator(name = "Entidade_Seq")
public class Compra {

	@Id
	@GeneratedValue(generator = "Entidade_Seq", strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long idCompra;

	private Long idPedido;

	private Integer tempoDePreparo;

	private String endereco;
	private LocalDate dataDeEntrega;
	private Long voucher;

	private CompraStatus status;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public CompraStatus getStatus() {
		return status;
	}

	public void setStatus(CompraStatus status) {
		this.status = status;
	}

	public LocalDate getDataDeEntrega() {
		return dataDeEntrega;
	}

	public void setDataDeEntrega(LocalDate dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}

	public Long getVoucher() {
		return voucher;
	}

	public void setVoucher(Long voucher) {
		this.voucher = voucher;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getTempoDePreparo() {
		return tempoDePreparo;
	}

	public void setTempoDePreparo(Integer tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}

}
