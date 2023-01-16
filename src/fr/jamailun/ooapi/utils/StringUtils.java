package fr.jamailun.ooapi.utils;

import java.util.StringJoiner;

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
	
	public static String toStringWhenNull(Object... objs) {
		StringJoiner sj = new StringJoiner(", ");
		for(int i = 0; i < objs.length - 1; i += 2) {
			if(objs[i] == null)
				sj.add(objs[i+1].toString());
		}
		return "[" + sj + "]";
	}
}
