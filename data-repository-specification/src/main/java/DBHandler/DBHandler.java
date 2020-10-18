package DBHandler;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;
import model.Entity;

/**
 * This class is bla bla
 * 
 * @author aut
 * 
 */

//param, author,return, throws
public abstract class DBHandler {
	private List<Entity> data;
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
		this.data = new ArrayList<>();
		this.entitiesPerFile = entitiesPerFile;
	}

	/**
	 * This is the description
	 * 
	 * @param path
	 * @return HashMap
	 */
	public abstract List<Entity> load(String path);

	/**
	 * This is the description
	 * 
	 * @param path
	 * @param data
	 */
	public abstract void dump(String path, List<Entity> data);

	/**
	 * This is the description
	 * 
	 * @param path
	 */
	public void loadData(String path) {
		File f = new File(path);
		List<Entity> output = new ArrayList<>();
		String[] pathnames = f.list();

		for (String pathname : pathnames) {
			File f2 = new File(path, pathname);
			try {
				List<Entity> loaded = load(f2.getPath());
				output.addAll(loaded);
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
		List<Entity> toStore = new ArrayList<>();

		for (Entity entity : data) {
			toStore.add(entity);
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
	public List<Entity> searchData(String key, Object value) {
		System.out.println("searching");
		ArrayList<Entity> matchedEntities = new ArrayList<>();
		for (Entity entity : data) {
			if (entity.getData().get(key).equals(value))
				matchedEntities.add(entity);
		}
		return matchedEntities;
	}

	/**
	 * This is the description
	 * 
	 * @param entity
	 */
	public void addEntity(Entity entity) {
		for (Entity e : data)
			if (e.getId().equals(entity.getId()))
				throw new KeyAlreadyExistsException();
		data.add(entity);
	}

	/**
	 * This is the description
	 * 
	 * @param key
	 */
	public void deleteEntity(String key) {
		for (Entity e : data)
			if (e.getId().equals(key))
				data.remove(e);
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
	 * @param entity
	 */
	public void editEntity(Entity entity) {
		for (Entity e : data) {
			if (e.getId().equals(entity.getId())) {
				data.set(data.indexOf(e), entity);
			}
		}
	}

	/**
	 * This is the description
	 * 
	 * @param keys
	 * @return HashMap
	 */
	public List<Entity> getData(ArrayList<String> keys) {
		 List<Entity> output = new ArrayList<>(data);
		 for (Entity entity : output) {
			 if (!keys.contains(entity.getId()))
				 output.remove(entity);
		 }
		return output;
	}

	/**
	 * This is the description
	 * 
	 * @return HashMap
	 */
	public List<Entity> getData() {
		return data;
	}

	/**
	 * This is the description
	 * 
	 * @param data
	 */
	public void setData(List<Entity> data) {
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
