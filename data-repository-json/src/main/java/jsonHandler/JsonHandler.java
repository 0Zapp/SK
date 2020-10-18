package jsonHandler;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DBHandler.DBHandler;
import model.Entity;

public class JsonHandler extends DBHandler {

	public JsonHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
	}

	@Override
	public List<Entity> load(String path) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Entity>>() {
		}.getType();
		List<Entity> map = gson.fromJson(path, type);
		return map;
	}

	@Override
	public void dump(String path, List<Entity> data) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(data);
	}

}
