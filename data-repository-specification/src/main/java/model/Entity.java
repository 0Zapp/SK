package model;

import java.io.Serializable;
import java.util.Map;

public class Entity implements Serializable {
	private String id;
	private String name;
	private Map<String, Object> data;
	
	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", data=" + data + "]";
	}

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

	public Entity() {
	}
	
	
}
