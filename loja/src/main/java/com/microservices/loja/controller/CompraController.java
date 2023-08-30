package com.microservices.loja.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.loja.controller.dto.CompraDto;
import com.microservices.loja.model.Compra;
import com.microservices.loja.service.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	private static final Logger LOG = LoggerFactory.getLogger(CompraController.class);

	@Autowired
	public CompraService compraService;

	@PostMapping
	public Compra realizarCompra(@RequestBody CompraDto compraDto) {
		LOG.info("Iniciando realizar Compra");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return compraService.realizaCompra(compraDto);
	}

	@GetMapping(value = "/{id}")
	public Compra findById(@PathVariable Long id) {
		LOG.info("Iniciando busca Compra");
		return compraService.findById(id);
	}

}
