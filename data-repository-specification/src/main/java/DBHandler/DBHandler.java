package DBHandler;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * This class is bla bla
 * 
 * @author aut
 * 
 */

//param, author,return, throws
public abstract class DBHandler {
	private HashMap<String, HashMap<String, Object>> data;
	private String basePath;
	private int entitiesPerFile;

	/**
	 * This is the description
	 * 
	 * @param path
	 * @param entitiesPerFile
	 * 
	 */
	public DBHandler(String path, int entitiesPerFile) {
		this.basePath = path;
		this.data = new HashMap<>();
		this.entitiesPerFile = entitiesPerFile;
	}

	/**
	 * This is the description
	 * 
	 * @param path
	 * @return HashMap
	 */
	public abstract HashMap<String, HashMap<String, Object>> load(String path);

	/**
	 * This is the description
	 * 
	 * @param path
	 * @param data
	 */
	public abstract void dump(String path, HashMap<String, HashMap<String, Object>> data);

	/**
	 * This is the description
	 * 
	 * @param path
	 */
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

	/**
	 * This is the description
	 * 
	 * @param path
	 */
	public void saveData(String path) {
		int storedElements = 0;
		int currentFile = 0;
		HashMap<String, HashMap<String, Object>> toStore = new HashMap<>();
		for (Map.Entry<String, HashMap<String, Object>> pair : data.entrySet()) {
			toStore.put(pair.getKey(), pair.getValue());
			storedElements++;
			if (entitiesPerFile > 0 && storedElements == entitiesPerFile) {
				dump(String.valueOf(currentFile), toStore);
				currentFile++;
				toStore.clear();
			}
		}
		;
		if (!toStore.isEmpty()) {
			dump(String.valueOf(currentFile), toStore);
		}
	};

	/**
	 * This is the description;
	 * 
	 * @param key
	 * @param value
	 * @return ArrayList
	 */
	public ArrayList<String> searchData(String key, Object value) {
		System.out.println("searching");
		ArrayList<String> matchedKeys = new ArrayList<>();
		for (Map.Entry<String, HashMap<String, Object>> pair : data.entrySet()) {
			HashMap<String, Object> entity = pair.getValue();
			if (entity.get(key).equals(value))
				matchedKeys.add(pair.getKey());
		}
		return matchedKeys;
	}

	/**
	 * This is the description
	 * 
	 * @param key
	 * @param entity
	 */
	public void addEntity(String key, HashMap<String, Object> entity) {
		if (data.containsKey(key))
			throw new KeyAlreadyExistsException();
		data.put(key, entity);
	}

	/**
	 * This is the description
	 * 
	 * @param key
	 */
	public void deleteEntity(String key) {
		data.remove(key);
	}

	/**
	 * This is the description
	 * 
	 * @param keys
	 */
	public void deleteEntities(ArrayList<String> keys) {
		for (String key : keys)
			deleteEntity(key);
	}

	/**
	 * This is the description
	 * 
	 * @param key
	 * @param entity
	 */
	public void editEntity(String key, HashMap<String, Object> entity) {
		data.put(key, entity);
	}

	/**
	 * This is the description
	 * 
	 * @param keys
	 * @return HashMap
	 */
	public HashMap<String, HashMap<String, Object>> getData(ArrayList<String> keys) {
		HashMap<String, HashMap<String, Object>> map = new HashMap<>(data);
		map.keySet().retainAll(keys);
		return map;
	}

	/**
	 * This is the description
	 * 
	 * @return HashMap
	 */
	public HashMap<String, HashMap<String, Object>> getData() {
		return data;
	}

	/**
	 * This is the description
	 * 
	 * @param data
	 */
	public void setData(HashMap<String, HashMap<String, Object>> data) {
		this.data = data;
	}

	/**
	 * This is the description
	 * 
	 * @return String
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * This is the description
	 * 
	 * @param basePath
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
