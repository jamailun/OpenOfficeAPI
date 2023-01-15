package fr.jamailun.ooapi.utils;

public final class XmlHelper {
	private XmlHelper() {}
	
	public static boolean isXmlCompatible(String string) {
		return string != null && string.matches("^[A-Za-z0-9\\-]+$");
	}
	
}
