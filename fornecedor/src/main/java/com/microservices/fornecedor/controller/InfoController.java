package com.microservices.fornecedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.fornecedor.model.InfoFornecedor;
import com.microservices.fornecedor.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

	@Autowired
	public InfoService infoService;

	@RequestMapping(value = "/{estado}", method = RequestMethod.GET)
	public InfoFornecedor getInfoEndereco(@PathVariable String estado) {
		return infoService.getInfoFornecedor(estado);

	}

}
