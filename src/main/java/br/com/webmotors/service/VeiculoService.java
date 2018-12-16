package br.com.webmotors.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webmotors.model.Veiculo;
import br.com.webmotors.response.VeiculoResponse;
import br.com.webmotors.transformer.CidadeTransformer;
import br.com.webmotors.transformer.ModeloTransformer;
import br.com.webmotors.transformer.VeiculoTransformer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoTransformer veiculoTransformer;
	
	@Autowired
	private ModeloTransformer modeloTransformer;
	
	@Autowired
	private CidadeTransformer cidadeTransformer;

	public VeiculoResponse getVeiculoResponse(String url) throws IOException {
		Response response = doGet(url); 
		return createResponse(response);
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
