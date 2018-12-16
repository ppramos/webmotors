package br.com.webmotors.response;

import java.util.List;

import br.com.webmotors.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponse {
	
	private List<Veiculo> veiculos;
	private List<String> modelos;
	private List<String> cidades;

}
