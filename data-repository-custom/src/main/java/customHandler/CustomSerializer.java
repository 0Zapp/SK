package customHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Entity;

public class CustomSerializer {
	public void write(List<Entity> data, BufferedWriter writer) throws Exception {
		System.out.println("writing...");
		for (Entity e : data) {
			writeEntity(e, writer);
		}
	}
	
	private void writeEntity(Entity e, BufferedWriter writer) throws Exception {
		writer.write(DataTags.Ent + "\n");
		writer.write("id = " + DataTags.Str + " " + e.getId() + "\n");
		writer.write("name = " + DataTags.Str + " " + e.getName() + "\n");
		writer.write("data = ");
		writeMap(e.getData(), writer);
		writer.write(DataTags.EE +"\n");
		System.out.println("wrote entity");
	}
	
	private void writeMap(Map<String, Object> m, BufferedWriter writer) throws Exception {
		writer.write(DataTags.Obj + "\n");
		
		for (Map.Entry<String, Object> entry : m.entrySet())  {
			String key = entry.getKey();
			Object value = entry.getValue();
			DataTags type;
			if (value instanceof Integer)
				type = DataTags.Int;
			else if (value instanceof Boolean)
				type = DataTags.Bool;
			else if (value instanceof String)
				type = DataTags.Str;
			else if (value instanceof Entity)
				type = DataTags.Ent;
			else throw new Exception();
			
			if (type == DataTags.Ent) {
				writer.write(key + " = ");
				writeEntity((Entity) value, writer);
			} else {
				writer.write(key + " = " + type + " " + value.toString() + "\n");
			}
		}
		
		writer.write(DataTags.EO + "\n");
	}
}
