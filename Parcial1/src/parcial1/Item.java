package Parcial1;
import java.util.HashMap;
import java.util.Map;
public class Item {
    private String key;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Map<String, Object> toMap() {
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put("id", id);
        itemMap.put("name", name);
        
        return itemMap;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
}
