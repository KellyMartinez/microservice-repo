package com.microservices.fornecedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.fornecedor.model.InfoFornecedor;
import com.microservices.fornecedor.repository.InfoRepository;

@Service
public class InfoService {
	
	@Autowired
	public InfoRepository infoRepository;
	
	public InfoFornecedor getInfoFornecedor(String estado){
		
		return infoRepository.findByEstado(estado);
	}

}
