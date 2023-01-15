package fr.jamailun.ooapi.odt;

import fr.jamailun.ooapi.utils.StringUtils;
import fr.jamailun.ooapi.xml.XmlAttributesMap;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ODProperties {
	
	private final Map<String, String> values = new HashMap<>();
	
	public void from(String name, XmlNode node) {
		from(name, node.getAttributes());
	}
	
	public void from(String name, XmlAttributesMap xmlAttributes) {
		values.put(name, xmlAttributes.get(name));
	}
	
	public String get(String name) {
		return values.get(name);
	}
	
	public void set(String name, String value) {
		if(value == null || value.isEmpty() || value.isBlank())
			values.remove(name);
		else
			values.put(name, value);
	}
	
	public String toXml() {
		StringJoiner sj = new StringJoiner(" ");
		for(Map.Entry<String, String> entry : values.entrySet()) {
			if(entry.getValue() != null)
				sj.add(entry.getKey()).add("=\"").add(StringUtils.escape(entry.getValue())).add("\"");
		}
		return sj.toString();
	}
	
	@Override
	public String toString() {
		return "ODProperties{" +
				"values=" + values +
				'}';
	}
}
