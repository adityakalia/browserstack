package org.hackathon.elite7.model;

public class Pair implements Comparable<Pair>{
	
	String key;
	String value;
	
	public Pair(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public int compareTo(Pair other) {
		return this.getKey().compareTo(other.getKey());
	}
	@Override
	public String toString() {
		return "{\"key\":\"" + key + "\", \"value\":" + value + "}";
	}	
	
}