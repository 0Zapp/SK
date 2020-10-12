package DBHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

public abstract class DBHandler {
	private HashMap<String, HashMap<String, Object>> data;
	private String basePath;
	private int entitiesPerFile;

	public DBHandler(String path, int entitiesPerFile) {
		this.basePath = path;
		this.data = new HashMap<>();
		this.entitiesPerFile = entitiesPerFile;
	}
	
	public abstract HashMap<String, HashMap<String, Object>> load(String path);
	
	public abstract void dump(String path, HashMap<String, HashMap<String, Object>> data);
	
	public void loadData(String path) {
		File f = new File(path);
		HashMap<String, HashMap<String, Object>> output = new HashMap<>();
        String[] pathnames = f.list();

        for (String pathname : pathnames) {
        	File f2 = new File(path, pathname);
        	try {        	
        		HashMap<String, HashMap<String, Object>> loaded = load(f2.getPath());        		
        		output.putAll(loaded);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
		this.data = output;
	};
	
	public void saveData(String path) {
		int storedElements = 0;
		int currentFile = 0;
		HashMap<String, HashMap<String, Object>> toStore = new HashMap<>();
		for (Map.Entry<String, HashMap<String, Object>> pair: data.entrySet()) {
            toStore.put(pair.getKey(), pair.getValue());
            storedElements++;
            if (entitiesPerFile > 0 && storedElements == entitiesPerFile) {
            	dump(String.valueOf(currentFile), toStore);
            	currentFile++;
            	toStore.clear();
            }
        };
        if (!toStore.isEmpty()) {
        	dump(String.valueOf(currentFile), toStore);
        }
	};
	
	// TODO
	public ArrayList<String> searchData() {
		System.out.println("searching");
		return new ArrayList<>();
	}
	
	public void addEntity(String key, HashMap<String, Object> entity) {
		if (data.containsKey(key))
			throw new KeyAlreadyExistsException();
		data.put(key, entity);
	}
	
	public void deleteEntity(String key) {
		data.remove(key);
	}
	
	public void deleteEntities(ArrayList<String> keys) {
		for (String key : keys)
			deleteEntity(key);
	}
	
	public void editEntity(String key, HashMap<String, Object> entity) {
		data.put(key, entity);
	}
	
	public HashMap<String, HashMap<String, Object>> getData(ArrayList<String> keys) {
		HashMap<String, HashMap<String, Object>> map = new HashMap<>(data);
		map.keySet().retainAll(keys);
		return map;
	}
	
	public HashMap<String, HashMap<String, Object>> getData() {
		return data;
	}

	public void setData(HashMap<String, HashMap<String, Object>> data) {
		this.data = data;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
