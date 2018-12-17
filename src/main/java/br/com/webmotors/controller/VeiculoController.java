package br.com.webmotors.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.webmotors.builder.URLBusca;
import br.com.webmotors.model.Veiculo;
import br.com.webmotors.response.VeiculoResponse;
import br.com.webmotors.service.VeiculoService;
import br.com.webmotors.service.config.VeiculoServiceConfig;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private VeiculoServiceConfig veiculoServiceConfig;
	
	@GetMapping("/buscar")
	public ResponseEntity<VeiculoResponse> getVeiculos(
			@RequestParam(required = true) String tipoveiculo,
			@RequestParam(required = false) String marca1,
			@RequestParam(required = false) String modelo1,
			@RequestParam(required = false) String anode,
			@RequestParam(required = false) String anoate ,
			@RequestParam(required = false) String cor,
			@RequestParam(required = false) String portas,
			@RequestParam(required = false) String anunciante,
			@RequestParam(required = false) String precode,
			@RequestParam(required = false) String precoate,
			@RequestParam(required = false) String kmde,
			@RequestParam(required = false) String kmate,
			@RequestParam(required = false) String cambio,
			@RequestParam(required = false) String combustivel,
			@RequestParam(required = false) String opcionais,
			@RequestParam(required = false) String estadocidade,
			@RequestParam(required = false) String p,
			@RequestParam(required = false) String o,
			@RequestParam(required = false) String qt) throws IOException {
		
		URLBusca urlBusca = URLBusca.builder()
			.tipoveiculo(tipoveiculo)
			.marca1(marca1)
			.modelo1(modelo1)
			.anode(anode)
			.anoate(anoate)
			.cor(cor)
			.portas(portas)
			.anunciante(anunciante)
			.precode(precode)
			.precoate(precoate)
			.kmde(kmde)
			.kmate(kmate)
			.cambio(cambio)
			.combustivel(combustivel)
			.opcionais(opcionais)
			.estadocidade(estadocidade)
			.o(o)
			.p(p)
			.qt(qt)
			.isAjax("true")
			.build();
		
		String url = urlBusca.build(veiculoServiceConfig.getUrl());
		VeiculoResponse veiculoResponse = veiculoService.getVeiculoResponse(url);
		return ResponseEntity.ok(veiculoResponse);
	}
	
	@GetMapping("/all")
	public List<Veiculo> findAll(@RequestParam(required = false) Integer pages) throws IOException {
		return veiculoService.findAll(pages);
	}
	
	
}
