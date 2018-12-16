package br.com.webmotors.transformer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import br.com.webmotors.constants.Constants;

@Component
public class CidadeTransformer {
	
public List<String> transform(JSONArray array) {
		
		List<String> result = new ArrayList<>();
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject jObject = array.getJSONObject(i);
			if (Constants.ATTR_LOCATION_SEARCH.equalsIgnoreCase(jObject.getString("AN"))) {
				JSONArray arrayModel = jObject.getJSONArray("R");
				for (int idx = 0; idx < arrayModel.length(); idx++) {
					JSONObject model = arrayModel.getJSONObject(idx);
					String value = model.getString("DV");
					JSONObject jsonCity = new JSONObject(value);
					String city = jsonCity.getString("City");
					result.add(city);
				}
			}
		}
		return result;
	}

}
