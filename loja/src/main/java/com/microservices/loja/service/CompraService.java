package com.microservices.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.loja.client.FornecedorClient;
import com.microservices.loja.client.TransportadorClient;
import com.microservices.loja.controller.dto.CompraDto;
import com.microservices.loja.controller.dto.InfoEntregaDto;
import com.microservices.loja.controller.dto.InfoFornecedorDto;
import com.microservices.loja.controller.dto.InfoPedidoDto;
import com.microservices.loja.controller.dto.VoucherDto;
import com.microservices.loja.model.Compra;
import com.microservices.loja.repository.CompraRepository;
import com.microservices.loja.util.CompraStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CompraService {
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private FornecedorClient client;

	@Autowired
	private TransportadorClient transportadorclient;
	
	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(threadPoolKey = "findById")
	public Compra findById(Long id) {
		LOG.info("Iniciando buscar Compra");
		return compraRepository.findById(id).orElse(new Compra());
	}

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompra")
	public Compra realizaCompra(CompraDto compraDto) {
		LOG.info("Iniciando busca de informações de endereço para estado {}", compraDto.getEndereco().getEstado());
		InfoFornecedorDto info = client.getInfoEndereco(compraDto.getEndereco().getEstado());

		Compra compraSalva = new Compra();
		compraSalva.setEndereco(compraDto.getEndereco().toString());
		compraSalva.setStatus(CompraStatus.PEDIDO_INICIADO);
		compraRepository.save(compraSalva);

		
		LOG.info("Iniciando realização do pedido no Fornecedor");
		InfoPedidoDto infoPedido = client.realizaPedido(compraDto.getItens());
		compraSalva.setIdPedido(infoPedido.getId());
		compraSalva.setTempoDePreparo(infoPedido.getTempoDePreparo());
		compraSalva.setStatus(CompraStatus.RESERVADO_FORNECEDOR);
		compraRepository.save(compraSalva);

		
		LOG.info("Iniciando processo de Transporte");
		InfoEntregaDto entrega = new InfoEntregaDto();
		entrega.setIdPedido(infoPedido.getId());
		entrega.setDataParaEnrega(LocalDate.now().plusDays(infoPedido.getTempoDePreparo()));
		entrega.setEnderecoOrigem(info.getEndereco());
		entrega.setEnderecoDestino(compraDto.getEndereco().toString());
		
		/*VoucherDto voucher = transportadorclient.realizaPedido(entrega);
		compraSalva.setDataDeEntrega(entrega.getDataParaEnrega());
		compraSalva.setVoucher(voucher.getNumero());*/
		compraSalva.setStatus(CompraStatus.PEDIDO_REALIZADO);

		compraRepository.save(compraSalva);

		LOG.info("Pedido realizado com sucesso, N. {}", compraSalva.getIdCompra());

		return compraSalva;

	}

	public Compra realizaCompraFallback(CompraDto compraDto) {
		Compra compraFallback = new Compra();
		compraFallback.setEndereco(compraDto.getEndereco().toString());
		return compraFallback;
	}

	/*
	 * @Autowired private RestTemplate client;
	 * 
	 * @Autowired private DiscoveryClient eurekaClient;
	 */
	/*
	 * USANDO REST TEMPLATE ResponseEntity<InfoFornecedorDto> response =
	 * client.exchange("http://fornecedor/info/"+
	 * compraDto.getEndereco().getEstado(), HttpMethod.GET,
	 * null,InfoFornecedorDto.class);
	 * 
	 * eurekaClient.getInstances("fornecedor").stream() .forEach(fornecedor -> {
	 * System.out.println("localhost:"+ fornecedor.getPort()); });
	 */
}
