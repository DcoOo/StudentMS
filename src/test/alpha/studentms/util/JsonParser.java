package test.alpha.studentms.util;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

import com.sun.javafx.collections.MappingChange.Map;

public class JsonParser {
	
	/**
	 * Json Parser
	 */
	
	/**
	 * parse json to other info map
	 * @param other_info
	 * @return
	 */
	public static HashMap<String, String> otherinfo2map(String other_info){
		
		JSONObject jsonObject = new JSONObject(other_info);
        HashMap<String, String> result = new HashMap<String, String>();
        Iterator<String> iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;
	}
	
	/**
	 * get post/reply/documents' id from collection
	 * @param collection
	 */
	public static Map<String, String[]> collection2ids(String collection){
		return null;
	}
	
	public static void main(String[] args){
		HashMap<String, String> map = otherinfo2map("{\"apple\": \"c\",\"cdd\": \"asdf\"}");
		System.out.println(map.keySet());
	}

}
