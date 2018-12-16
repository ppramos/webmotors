package br.com.webmotors.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public abstract class Transformer<T> {
	
	public List<T> transform(JSONArray array) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
	    	String html = array.getString(i);
	    	Element root = Jsoup.parse(html, "", Parser.xmlParser());
	    	T veiculo = build(root);
	    	result.add(veiculo);
	    }
		return result;
	}
	
	protected abstract T build(Element element);
	
	protected String getOwnText(Element element, String pattern) {
		Element first = getFirst(element, pattern);
		if (first != null) {
			return first.ownText();
		}
		return null;
	}
	
	protected List<String> getListOwnText(Element element, String pattern) {
		Elements select = element.select(pattern);
		List<String> list = new ArrayList<>();
		for (Element el: select) {
			list.add(el.ownText());
		}
		return list;
	}
	
	protected String getAttr(Element element, String pattern, String attr) {
		Element first = getFirst(element, pattern);
		if (first != null) {
			return first.attr(attr);
		}
		return null;
		
	}
	
	protected Element getFirst(Element element, String pattern) {
		Elements select = element.select(pattern);
		if (CollectionUtils.isNotEmpty(select)) {
			return select.first();
		}
		return null;
	}

}
