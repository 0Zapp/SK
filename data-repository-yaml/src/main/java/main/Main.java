package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Entity;
import yamlHandler.YamlHandler;

public class Main {

	public static void main(String[] args) {
		YamlHandler yh = new YamlHandler("C:\\Users\\rektix\\Desktop\\test.yml", 10);
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
		yh.dump("test.yaml", data);
		System.out.println("Dumped");
		yh.load("test.yaml");
		System.out.println(data);

	}

}
