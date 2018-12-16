package br.com.webmotors.transformer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import br.com.webmotors.constants.Constants;

@Component
public class ModeloTransformer {
	
	public List<String> transform(JSONArray array) {
		
		List<String> result = new ArrayList<>();
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject jObject = array.getJSONObject(i);
			if (Constants.ATTR_MODEL.equalsIgnoreCase(jObject.getString("AN"))) {
				JSONArray arrayModel = jObject.getJSONArray("R");
				for (int idx = 0; idx < arrayModel.length(); idx++) {
					JSONObject model = arrayModel.getJSONObject(idx);
					result.add(model.getString("V"));
				}
			}
		}
		return result;
	}

}
