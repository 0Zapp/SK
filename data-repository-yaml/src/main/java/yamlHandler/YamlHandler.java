package yamlHandler;

import java.io.InputStream;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import DBHandler.DBHandler;
import model.Entity;

public class YamlHandler extends DBHandler {

	public YamlHandler(String path, int entitiesPerFile) {
		super(path, entitiesPerFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Entity> load(String path) {
		Yaml yaml = new Yaml();
		InputStream inputStream = this.getClass()
		  .getClassLoader()
		  .getResourceAsStream(path);
		List<Entity> obj = yaml.load(inputStream);
		System.out.println(obj);
		return obj;
	}

	@Override
	public void dump(String path, List<Entity> data) {
		// TODO Auto-generated method stub
		
	}

}
