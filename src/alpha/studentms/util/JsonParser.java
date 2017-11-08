package alpha.studentms.util;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	

	/**
	 * collection jsonstr -> List
	 * @param json
	 * @return
	 */
	public static List<Object> toList(String json){
		JSONArray jsonArray = new JSONArray(json);
		List<Object> list = jsonArray.toList();
		return list;
	}
	
	/**
	 * otherinfo jsonstr -> Map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json){
		JSONObject object = new JSONObject(json);
		return object.toMap();
	}
	
	public static void main(String[] args){
//		String json = "[\"asdf\",\"ffda\", \"dddd\"]";
//		List<Object> list = toList(json);
//		System.out.println(list.get(0));
		String jsonMap = "{\"ads\":\"dddd\", \"ffff\":\"ddd\"}";
		Map<String, Object> map = toMap(jsonMap);
		System.out.println(map.keySet());
	}

}
