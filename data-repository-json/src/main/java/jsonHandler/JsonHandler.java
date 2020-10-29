package jsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DBHandler.DBHandler;
import model.Entity;

public class JsonHandler extends DBHandler {

	public JsonHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
	}

	public JsonHandler(String path) throws Exception {
		super(path);
	}

	@Override
	protected List<Entity> load(String path) {
		List<Entity> map = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			Gson gson = new Gson();
			Type type = new TypeToken<List<Entity>>() {
			}.getType();
			map = gson.fromJson(reader, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	protected void dump(String path, List<Entity> data) {
		try {
			Writer writer = new FileWriter(path);
			Gson gson = new Gson();
			gson.toJson(data, writer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
