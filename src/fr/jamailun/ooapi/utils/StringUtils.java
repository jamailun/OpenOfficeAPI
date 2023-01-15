package fr.jamailun.ooapi.utils;

public final class StringUtils {
	private StringUtils() {}
	public static String escape(String s){
		return s.replace("\\", "\\\\")
				.replace("\t", "\\t")
				.replace("\b", "\\b")
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\f", "\\f")
				.replace("\'", "\\'")
				.replace("\"", "\\\"");
	}
}
