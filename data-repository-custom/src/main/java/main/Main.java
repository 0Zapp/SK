package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import customHandler.CustomSerializer;
import model.Entity;

public class Main {

	public static void main(String[] args) {
		CustomSerializer cs = new CustomSerializer();
		FileWriter writer;
		ArrayList<Entity> data = new ArrayList<>();
		HashMap<String, Object> edata = new HashMap<>();
		edata.put("abc", "def");
		edata.put("ats", 523);
		edata.put("wp", true);
		edata.put("obj", new Entity("qwfp", "luyl", new HashMap<>()));
		Entity e = new Entity("abc", "abcsd", edata);
		data.add(e);
		try {
			writer = new FileWriter("abc.txt");
			BufferedWriter br = new BufferedWriter(writer);
			cs.write(data, br);
			br.close();
			writer.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
