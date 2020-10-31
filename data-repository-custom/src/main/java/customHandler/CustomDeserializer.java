package customHandler;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Entity;

public class CustomDeserializer {
	public List<Entity> read(BufferedReader reader) throws Exception {
		String line = reader.readLine();
		ArrayList<Entity> l = new ArrayList<>();
		while (line != null) {
			if (line.equals(DataTags.Ent.toString()))
				l.add(readEntity(reader));
			else
				throw new Exception();
			line = reader.readLine();
		}
		return l;
	}
	
	
	private Entity readEntity(BufferedReader reader) throws Exception {
		String line = reader.readLine();
		Entity e = new Entity();
		String attributeName;
		while (!line.equals(DataTags.EE.toString())) {
			String[] s1 = line.split(" = ", 2);
			attributeName = s1[0];
			String[] s2 = s1[1].split(" ", 2);
			switch (attributeName) {
			case "id":
				e.setId(s2[1]);
				break;
			case "name":
				e.setName(s2[1]);
				break;
			case "data":
				e.setData(readMap(reader));
				break;
			default:
				throw new Exception();
			}
			
			line = reader.readLine();
		}
		
		return e;
	}
	
	private Map<String, Object> readMap(BufferedReader reader) throws Exception {
		String line = reader.readLine();
		String attributeName;
		DataTags type;
		Object attributeValue;
		HashMap<String, Object> m = new HashMap<>();
		
		while (!line.equals(DataTags.EO.toString())) {
			String[] s1 = line.split(" = ", 2);
			attributeName = s1[0];
			String[] s2 = s1[1].split(" ", 2);
			
			type = DataTags.valueOf(s2[0]);
			switch (type) {
			case Int:
				attributeValue = Integer.valueOf(s2[1]);
				break;
			case Bool:
				attributeValue = Boolean.valueOf(s2[1]);
				break;
			case Str:
				attributeValue = s2[1];
				break;
			case Ent:
				attributeValue = readEntity(reader);
				break;
			default:
				throw new Exception();
			}
			m.put(attributeName, attributeValue);
			
			line = reader.readLine();
		}
		
		return m;
	}
}
