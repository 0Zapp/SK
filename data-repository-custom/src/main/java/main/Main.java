package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import customHandler.CustomDeserializer;
import customHandler.CustomSerializer;
import model.Entity;

public class Main {

	public static void main(String[] args) {
		String fileName = "abc.txt";
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
			writer = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(writer);
			cs.write(data, br);
			br.close();
			writer.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			CustomDeserializer cd = new CustomDeserializer();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<Entity> l = (ArrayList<Entity>) cd.read(br);
			System.out.println(l);
			
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
