package yamlHandler;

import java.util.HashMap;

import DBHandler.DBHandler;

public class YamlHandler extends DBHandler {

	public YamlHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap<String, HashMap<String, Object>> load(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dump(String path, HashMap<String, HashMap<String, Object>> data) {
		// TODO Auto-generated method stub
		
	}

}
