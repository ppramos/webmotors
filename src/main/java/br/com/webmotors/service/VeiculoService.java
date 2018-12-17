package br.com.webmotors.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webmotors.builder.URLBusca;
import br.com.webmotors.model.Veiculo;
import br.com.webmotors.response.VeiculoResponse;
import br.com.webmotors.service.config.VeiculoServiceConfig;
import br.com.webmotors.transformer.CidadeTransformer;
import br.com.webmotors.transformer.ModeloTransformer;
import br.com.webmotors.transformer.VeiculoTransformer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class VeiculoService {
	
	private static Logger logger = LoggerFactory.getLogger(VeiculoService.class.getClass());

	@Autowired
	private VeiculoTransformer veiculoTransformer;
	
	@Autowired
	private ModeloTransformer modeloTransformer;
	
	@Autowired
	private CidadeTransformer cidadeTransformer;
	
	@Autowired
	private VeiculoServiceConfig veiculoServiceConfig;

	public VeiculoResponse getVeiculoResponse(String url) throws IOException {
		Response response = doGet(url); 
		return createResponse(response);
	}
	
	public List<Veiculo> findAll(Integer pages) throws IOException {
		
		List<Veiculo> listaVeiculos = new ArrayList<>();
		List<Veiculo> result = new ArrayList<>();
		Integer page = 0;
		while (listaVeiculos != null && (pages == null || page < pages)) {
			String url = createUrlByPage(++page);
			logger.info("Busca em {}", url);
			Response response = doGet(url); 
			JSONObject jObject = new JSONObject(response.body().string());
			listaVeiculos = findVeiculos(jObject);
			if (CollectionUtils.isNotEmpty(listaVeiculos)) {
				result.addAll(listaVeiculos);
			}
		}
		return result;
		
	}
	
	private String createUrlByPage(Integer page) throws UnsupportedEncodingException {
		URLBusca urlBusca = URLBusca.builder()
				.tipoveiculo("carros")
				.o("1")
				.p(String.valueOf(page))
				.isAjax("true")
				.build();
		return urlBusca.build(veiculoServiceConfig.getUrl());
	}
	
	private VeiculoResponse createResponse(Response response) throws JSONException, IOException {
		JSONObject jObject = new JSONObject(response.body().string());
		return VeiculoResponse.builder()
				.modelos(findModelos(jObject))
				.cidades(findCidades(jObject))
				.veiculos(findVeiculos(jObject))
				.build();
	}
	
	private List<Veiculo> findVeiculos(JSONObject jObject) {
		if (!jObject.isNull("AD")) {
			JSONArray jArray = jObject.getJSONArray("AD");
			return veiculoTransformer.transform(jArray);
		}
		return null;
		
	}
	
	private List<String> findCidades(JSONObject jObject) {
		if (!jObject.isNull("AD")) {
			if (!jObject.isNull("N")) {
				JSONArray array = jObject.getJSONArray("N");
				return cidadeTransformer.transform(array);
			}
		}
		return null;
		
	}
	
	private List<String> findModelos(JSONObject jObject) {
		
		if (!jObject.isNull("AD")) {
			if (!jObject.isNull("N")) {
				JSONArray array = jObject.getJSONArray("N");
				return modeloTransformer.transform(array);
			}
		}
		return null;
		
	}
	
	private Response doGet(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .build();

		return client.newCall(request).execute();
	}

}
