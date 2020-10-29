package yamlHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import DBHandler.DBHandler;
import model.Entity;

public class YamlHandler extends DBHandler {

	public YamlHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
		// TODO Auto-generated constructor stub
	}

	public YamlHandler(String path) throws Exception {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Entity> load(String path) {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(path));
			Yaml yaml = new Yaml();
			List<Entity> obj = (List<Entity>) yaml.load(inputStream);
			return obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void dump(String path, List<Entity> data) {
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);
		Yaml yaml = new Yaml(options);
		FileWriter writer;
		try {
			writer = new FileWriter(path);
			yaml.dump(data, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
