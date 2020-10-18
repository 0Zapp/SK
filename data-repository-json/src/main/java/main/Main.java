package main;


import java.util.List;

import jsonHandler.JsonHandler;
import model.Entity;

public class Main {

	public static void main(String[] args) {
		JsonHandler jh = new JsonHandler("test.json", 10);
		List<Entity> data = jh.load("test.json");
		System.out.println(data);

	}

}
