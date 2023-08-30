package com.microservices.loja.controller.dto;

import java.util.List;

public class CompraDto {

	private List<ItemCompraDto> itens;
	private EnderecoDto endereco;
	public List<ItemCompraDto> getItens() {
		return itens;
	}
	public void setItens(List<ItemCompraDto> itens) {
		this.itens = itens;
	}
	public EnderecoDto getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	
}
