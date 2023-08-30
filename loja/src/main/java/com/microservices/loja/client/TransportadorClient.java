package com.microservices.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.loja.controller.dto.InfoEntregaDto;
import com.microservices.loja.controller.dto.VoucherDto;

@FeignClient("transportador")
public interface TransportadorClient {

	@RequestMapping(path = "/entrega", method = RequestMethod.POST)
	VoucherDto realizaPedido(InfoEntregaDto entregaDTO);
}
