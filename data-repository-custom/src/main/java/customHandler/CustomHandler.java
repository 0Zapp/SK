package customHandler;

import java.util.List;

import DBHandler.DBHandler;
import model.Entity;

public class CustomHandler extends DBHandler {

	public CustomHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dump(String path, List<Entity> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entity> load(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
