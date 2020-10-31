package customHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
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
		List<Entity> map = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	protected void dump(String path, String fileName, List<Entity> data) {
		try {
			FileWriter writer = new FileWriter(path + "\\" + fileName);
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
