package DBHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	private final String config = "config.txt";

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
		try {
			FileWriter fw = new FileWriter(new File(path, config));
			fw.write(String.valueOf(entitiesPerFile));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	 * This is the description
	 * 
	 * @param path
	 * @return HashMap
	 */
	protected abstract List<Entity> load(String path);

	/**
	 * This is the description
	 * 
	 * @param path
	 * @param fileName
	 * @param data
	 */
	protected abstract void dump(String path, String fileName, List<Entity> data);

	public void loadData() {
		loadData(basePath);
	}

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
			if (pathname.equals(config))
				continue;
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

	public void saveData() {
		saveData(basePath);
	}

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
			if (entity.getData().get(key) != null && entity.getData().get(key).equals(value))
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
		for (Entity e : data) {
			if (e.getId().equals(key)) {
				data.remove(e);
				return;
			}
		}
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
		List<Entity> output = new ArrayList<>();
		for (Entity entity : data) {
			if (keys.contains(entity.getId()))
				output.add(entity);
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

	public int getEntitiesPerFile() {
		return entitiesPerFile;
	}

	public void deleteEntities(List<Entity> input) {
		for (Entity e : input) {
			data.remove(e);
		}

	}
}
