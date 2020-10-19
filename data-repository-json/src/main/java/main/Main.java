package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jsonHandler.JsonHandler;
import model.Entity;

public class Main {

	public static void main(String[] args) {
		JsonHandler yh = new JsonHandler("sdfsdfd", 10);
		List<Entity> data = new ArrayList<Entity>();
		Entity e = new Entity();
		e.setName("test");
		e.setId("123");
		HashMap<String, Object> map = new HashMap<>();
		map.put("abc", "def");
		map.put("zxv", 123);
		map.put("wpf", true);
		e.setData(map);
		data.add(e);
		yh.dump("test.json", data);
		data=yh.load("test.json");
		System.out.println(data);
	}

}
