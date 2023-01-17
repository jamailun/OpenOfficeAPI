package fr.jamailun.ooapi.odt;

import fr.jamailun.ooapi.utils.StringUtils;

public interface TextContainer {
	
	String getText();
	
	void setText(String value);
	
	default int countOccurences(String str) {
		return StringUtils.findOccurences(getText(), str);
	}
	
	default void replace(String toFind, String replaceWith) {
		setText(getText().replaceAll(toFind, replaceWith));
	}
	
}
