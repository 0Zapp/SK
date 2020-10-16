package jsonHandler;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DBHandler.DBHandler;

public class JsonHandler extends DBHandler {

	public JsonHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
	}

	@Override
	public HashMap<String, HashMap<String, Object>> load(String path) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, HashMap<String, Object>>>() {
		}.getType();
		HashMap<String, HashMap<String, Object>> map = gson.fromJson(path, type);
		return map;
	}

	@Override
	public void dump(String path, HashMap<String, HashMap<String, Object>> data) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(data);
	}

}
