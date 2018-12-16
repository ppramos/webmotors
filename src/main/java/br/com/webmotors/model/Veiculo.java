package br.com.webmotors.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
	private String codigo;
	private String nome;
	private String descricao;
	private String transmissao;
	private String anoModelo;
	private String quilometragem;
	private String preco;
	private String imagem;
	private String estadoCidade;
	private List<String> informacoes;
	private String anunciante;

}
