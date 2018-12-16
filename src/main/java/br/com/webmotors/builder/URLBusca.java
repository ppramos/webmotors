package br.com.webmotors.builder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLBusca {
	
	private String tipoveiculo;
	private String marca1;
	private String modelo1;
	private String anode;
	private String anoate;
	private String cor;
	private String portas;
	private String anunciante;
	private String precode;
	private String precoate;
	private String kmde;
	private String kmate;
	private String cambio;
	private String combustivel;
	private String opcionais;
	private String estadocidade;
	private String p;
	private String o;
	private String qt;
	private String isAjax;
	
	public String build(String path) throws UnsupportedEncodingException {
		List<String> params = new ArrayList<>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field: fields) {
			Object object = null;
			try {
				object = field.get(this);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch blockS
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (object != null) {
				String value = (String) object;
				String name = field.getName();
				params.add(name + "=" + URLEncoder.encode(value, "UTF-8"));
			}
		}
		return path + tipoveiculo + "?" +  StringUtils.join(params, "&");
	}
		 
	/*https://www.webmotors.com.br/carros/rs-viamao/chevrolet/celta/de.2003/cor.branco/portas.2?
		tipoveiculo
		marca1
		modelo1
		anode
		anoate
		cor
		portas
		anunciante
		precode
		precoate
		kmde
		kmate
		cambio
		combustivel
		opcionais
		estadocidade
		p
		o
		qt
		isAjax*/
	
	

}
