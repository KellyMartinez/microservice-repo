package com.microservices.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.loja.controller.dto.InfoFornecedorDto;
import com.microservices.loja.controller.dto.InfoPedidoDto;
import com.microservices.loja.controller.dto.ItemCompraDto;


@FeignClient("fornecedor")//id da aplicação que quer acessar
public interface FornecedorClient {
	
	@RequestMapping(value = "/info/{estado}")
	InfoFornecedorDto getInfoEndereco(@PathVariable String estado);

	@RequestMapping(method = RequestMethod.POST,value = "/pedido")
	InfoPedidoDto realizaPedido(List<ItemCompraDto> itens);

}
