package br.com.webmotors.transformer;

import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_ANO_MODELO;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_CODIGO;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_DESCRICAO;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_ESTADO_CIDADE;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_INFORMACOES;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_PRECO;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_QUILOMETRAGEM;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_TRANSMISSAO;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_NOME;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_IMAGEM;
import static br.com.webmotors.constants.Patterns.PATTERN_BUSCA_ANUNCIANTE;

import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import br.com.webmotors.model.Veiculo;

@Component
public class VeiculoTransformer extends Transformer<Veiculo> {
	
	private static final String ATTR_CODIGO = "data-codanuncio";
	private static final String ATTR_IMAGEM = "data-original";
	
	protected Veiculo build(Element root) {
		return Veiculo.builder()
				.nome(getNome(root))
	    		.anoModelo(getAnoModelo(root))
	    		.codigo(getCodigo(root))
	    		.preco(getPreco(root))
	    		.descricao(getDescricao(root))
	    		.estadoCidade(getEstadoCidade(root))
	    		.informacoes(getInformacoes(root))
	    		.quilometragem(getQuilometragem(root))
	    		.transmissao(getTransmissao(root))
	    		.imagem(getImagem(root))
	    		.anunciante(getAnunciante(root))
	    		.build();
		
	}
	
	private String getNome(Element element) {
		return getOwnText(element, PATTERN_BUSCA_NOME);
	}
	
	private String getPreco(Element element) {
		return getOwnText(element, PATTERN_BUSCA_PRECO);
	}
	
	private String getDescricao(Element element) {
		return getOwnText(element, PATTERN_BUSCA_DESCRICAO);
	}	
	
	private String getAnoModelo(Element element) {
		return getOwnText(element, PATTERN_BUSCA_ANO_MODELO);
	}
	
	private String getQuilometragem(Element element) {
		return getOwnText(element, PATTERN_BUSCA_QUILOMETRAGEM);
	}
	
	private String getTransmissao(Element element) {
		return getOwnText(element, PATTERN_BUSCA_TRANSMISSAO);
	}
	
	private String getCodigo(Element element) {
		return getAttr(element, PATTERN_BUSCA_CODIGO, ATTR_CODIGO);
	}

	private String getEstadoCidade(Element element) {
		return getOwnText(element, PATTERN_BUSCA_ESTADO_CIDADE);
	}
	
	private List<String> getInformacoes(Element element) {
		return getListOwnText(element, PATTERN_BUSCA_INFORMACOES);
	}
	
	private String getImagem(Element element) {
		return getAttr(element, PATTERN_BUSCA_IMAGEM, ATTR_IMAGEM);
	}
	
	private String getAnunciante(Element element) {
		return getOwnText(element, PATTERN_BUSCA_ANUNCIANTE);
	}
}
