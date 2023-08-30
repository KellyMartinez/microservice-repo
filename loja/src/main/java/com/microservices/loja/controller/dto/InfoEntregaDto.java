package com.microservices.loja.controller.dto;

import java.time.LocalDate;

public class InfoEntregaDto {

	private Long pedidoId;
	private LocalDate dataParaEnrega;
	private String enderecoOrigem;
	private String enderecoDestino;

	public Long getIdPedido() {
		return pedidoId;
	}

	public void setIdPedido(Long idPedido) {
		this.pedidoId = idPedido;
	}

	public LocalDate getDataParaEnrega() {
		return dataParaEnrega;
	}

	public void setDataParaEnrega(LocalDate dataParaEnrega) {
		this.dataParaEnrega = dataParaEnrega;
	}

	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}

	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

}
