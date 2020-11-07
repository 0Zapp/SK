package DBHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;
import model.Entity;

/**
 * This class is used for handling files
 * 
 * @author Marko Nedeljkovic, Mihajlo Krsmanovic
 * 
 */

//param, author,return, throws
public abstract class DBHandler {
	private List<Entity> data;
	private String basePath;
	private int entitiesPerFile;
	private final String config = "config.txt";

	/**
	 * Constructor
	 * 
	 * @param path            database directory path.
	 * @param entitiesPerFile number of enteties per singular file.
	 * 
	 */
	public DBHandler(String path, int entitiesPerFile) {
		this.basePath = path;
		this.data = new ArrayList<>();
		this.entitiesPerFile = entitiesPerFile;
		try {
			FileWriter fw = new FileWriter(new File(path, config));
			fw.write(String.valueOf(entitiesPerFile));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor
	 * 
	 * @param path database directory path.
	 * 
	 */
	public DBHandler(String path) throws Exception {
		File f = new File(path, config);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine().trim();
			int epf = Integer.parseInt(line);
			this.basePath = path;
			this.data = new ArrayList<>();
			this.entitiesPerFile = epf;
			br.close();
		} catch (Exception e) {
			throw new Exception("Unable to open config file.");
		}

	}

	/**
	 * Method used for loading entities into memory.
	 * 
	 * @param path path to the collection.
	 * 
	 * @return List<Entity> list of entities that are loaded.
	 */
	protected abstract List<Entity> load(String path);

	/**
	 * Method used for putting data into the designated location.
	 * 
	 * @param path     path where data will be saved.
	 * @param fileName name of the file in which our data will be saved.
	 * @param data     List of entities to be saved.
	 */
	protected abstract void dump(String path, String fileName, List<Entity> data);

	/**
	 * Method used for loading entities into memory from base path.
	 * 
	 */

	public void loadData() {
		loadData(basePath);
	}

	/**
	 * Method used for loading entities into memory from designated path.
	 * 
	 * @param path designated location from where the entities are loaded.
	 */
	public void loadData(String path) {
		File f = new File(path);
		List<Entity> output = new ArrayList<>();
		String[] pathnames = f.list();

		for (String pathname : pathnames) {
			if (pathname.equals(config))
				continue;
			File f2 = new File(path, pathname);
			try {
				List<Entity> loaded = load(f2.getPath());
				if (loaded != null) {
					output.addAll(loaded);
				}
			} catch (Exception e) {
				System.out.println("added nothing");
			}

		}
		this.data = output;
	};

	/**
	 * Method used for saving data into base location.
	 * 
	 */

	public void saveData() {
		saveData(basePath);
	}

	/**
	 * Method used for saving data into designated location.
	 * 
	 * @param path Designated location where data will be stored.
	 */
	public void saveData(String path) {
		int storedElements = 0;
		int currentFile = 0;
		List<Entity> toStore = new ArrayList<>();

		for (Entity entity : data) {
			toStore.add(entity);
			storedElements++;
			if (entitiesPerFile > 0 && toStore.size() == entitiesPerFile) {
				dump(path, String.valueOf(currentFile), toStore);
				currentFile++;
				toStore.clear();
			}
		}
		if (!toStore.isEmpty()) {
			dump(path, String.valueOf(currentFile), toStore);
		}
	};

	/**
	 * Method used for searching through data.
	 * 
	 * @param key   represents the atribute which we intent to search on.
	 * @param value represents the value of the atribute we are searching on.
	 * @return ArrayList of all the entities that match the description.
	 */
	public List<Entity> searchData(String key, Object value) {
		ArrayList<Entity> matchedEntities = new ArrayList<>();
		for (Entity entity : data) {
			if (entity.getData().get(key) != null && entity.getData().get(key).equals(value))
				matchedEntities.add(entity);
		}
		return matchedEntities;
	}

	public List<Entity> searchData(Map<String, Object> terms) {
		ArrayList<Entity> matchedEntities = new ArrayList<>();
		for (Entity entity : data) {
			for (Map.Entry<String, Object> entry : terms.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (entity.getData().get(key) != null && entity.getData().get(key).equals(value)) {
					matchedEntities.add(entity);
					break;
				}
			}
		}
		return matchedEntities;
	}

	public List<Entity> sortById(List<Entity> inData, boolean ascending) {
		if (ascending)
			Collections.sort(inData);
		else
			Collections.reverse(inData);
		return inData;
	}

	public Entity searchById(String key) {
		for (Entity entity : data) {
			if (entity.getId().equals(key))
				return entity;
		}
		return null;
	}

	public List<Entity> searchByName(String key) {
		ArrayList<Entity> matchedEntities = new ArrayList<>();
		for (Entity entity : data) {
			if (entity.getName().equals(key))
				matchedEntities.add(entity);
		}
		return matchedEntities;
	}

	/**
	 * Method used for adding additional entities.
	 * 
	 * @param entity entitiy which we intend to add.
	 */
	public void addEntity(Entity entity) {
		for (Entity e : data)
			if (e.getId().equals(entity.getId()))
				throw new KeyAlreadyExistsException();
		data.add(entity);
	}

	/**
	 * Method used for removing a single entity.
	 * 
	 * @param key id of the entity we wish to remove.
	 * @return if the entity is deleted or not
	 */
	public boolean deleteEntity(String key) {
		for (Entity e : data) {
			if (e.getId().equals(key)) {
				data.remove(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method used for removing multiple entities.
	 * 
	 * @param keys ids of the entities we wish to remove.
	 */
	public void deleteEntities(ArrayList<String> keys) {
		for (String key : keys)
			deleteEntity(key);
	}

	/**
	 * Method used for modifiying a single entity.
	 * 
	 * @param entity the entity we wish to edit.
	 * @return if the entity was edited or not
	 */
	public boolean editEntity(Entity entity) {
		for (Entity e : data) {
			if (e.getId().equals(entity.getId())) {
				data.set(data.indexOf(e), entity);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method used for obtaining data.
	 * 
	 * @param keys list of ids of the entities we wish to get.
	 * @return List list of entities that match the keys.
	 */
	public List<Entity> getData(ArrayList<String> keys) {
		List<Entity> output = new ArrayList<>();
		for (Entity entity : data) {
			if (keys.contains(entity.getId()))
				output.add(entity);
		}
		return output;
	}

	/**
	 * Method used for obtaining data.
	 * 
	 * @return List list of all entities.
	 */
	public List<Entity> getData() {
		return data;
	}

	/**
	 * Set data to a new list.
	 * 
	 * @param data new entities we wish to set our current collection to.
	 */
	public void setData(List<Entity> data) {
		this.data = data;
	}

	/**
	 * Method used for obtaining our current Path.
	 * 
	 * @return String the path.
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * method used for setting our path.
	 * 
	 * @param basePath path we wish to set our collection to.
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * method used for setting the amount of entities we wish to have per one file.
	 * 
	 * @param entitiesPerFile number of entities.
	 */
	public void setEntitiesPerFile(int entitiesPerFile) {
		this.entitiesPerFile = entitiesPerFile;
		try {
			FileWriter fw = new FileWriter(new File(basePath, config));
			fw.write(String.valueOf(entitiesPerFile));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method used for getting the amount of entities we wish to have per one file.
	 * 
	 * @return entitiesPerFile number of entities.
	 */
	public int getEntitiesPerFile() {
		return entitiesPerFile;
	}

	/**
	 * method used for deleting a collection of entities.
	 * 
	 * @param input entities we wish to delete.
	 */
	public void deleteEntities(List<Entity> input) {
		for (Entity e : input) {
			data.remove(e);
		}

	}
}
