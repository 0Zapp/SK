package model;

import java.util.Map;

public class Entity {
	private String id;
	private String name;
	private Map<String, Object> data;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Entity(String id, String name, Map<String, Object> data) {
		this.id = id;
		this.name = name;
		this.data = data;
	}
	
	
}