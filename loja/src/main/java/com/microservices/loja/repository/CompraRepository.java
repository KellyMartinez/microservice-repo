package com.microservices.loja.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.loja.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{

	Optional<Compra> findById(Long id);
}
