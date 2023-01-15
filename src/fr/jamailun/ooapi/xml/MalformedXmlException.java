package fr.jamailun.ooapi.xml;

public final class MalformedXmlException extends RuntimeException {
	
	public MalformedXmlException(String msg) {
		super("Malformed XML : " + msg);
	}
	
}
