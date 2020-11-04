package model;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class Entity implements Serializable, Comparable<Entity> {
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

	public Entity(String id, String name, Map<String, Object> data) {
		this.id = id;
		this.name = name;
		this.data = data;
	}
	
	public Entity(String name, Map<String, Object> data) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.data = data;
	}

	@Override
	public int compareTo(Entity e) {
		if (getId() == null || e.getId() == null) {
			return 0;
		}
		return getId().compareTo(e.getId());
	}

}
