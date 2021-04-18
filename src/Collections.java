import java.util.HashMap;
import java.util.Map;

public class Collections {

	public static void main(String[] args) {
		
        Map<String, String> map = getMap();
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("one", "two");
        map.putAll(map2);
    }

    public static Map<String, String> getMap() {
        return java.util.Collections.emptyMap();
	}
	

}
