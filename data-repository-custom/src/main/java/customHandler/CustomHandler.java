package customHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import DBHandler.DBHandler;
import model.Entity;

public class CustomHandler extends DBHandler {

	public CustomHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
	}

	public CustomHandler(String path) throws Exception {
		super(path);
	}

	@Override
	protected List<Entity> load(String path) {
		if(!path.endsWith(".custom")) {
			return null;
		}
		List<Entity> map = null;
		try {
			CustomDeserializer cd = new CustomDeserializer();
			BufferedReader reader = Files.newBufferedReader(Paths.get(path));
			map = cd.read(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	protected void dump(String path, String fileName, List<Entity> data) {
		try {
			FileWriter writer = new FileWriter(path + "\\" + fileName + ".custom");
			BufferedWriter br = new BufferedWriter(writer);
			CustomSerializer cs = new CustomSerializer();
			cs.write(data, br);
			br.close();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
