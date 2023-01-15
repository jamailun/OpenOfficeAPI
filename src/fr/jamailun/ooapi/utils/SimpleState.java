package fr.jamailun.ooapi.utils;

public enum SimpleState {
	BEFORE,
	CURRENT,
	AFTER;
	
	public SimpleState before() {
		return this == AFTER ? CURRENT : BEFORE;
	}
	
	public SimpleState after() {
		return this == BEFORE ? CURRENT : AFTER;
	}
}
