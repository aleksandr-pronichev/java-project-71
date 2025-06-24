package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public static Map<String, Map<String, Object>> build(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        Map<String, Map<String, Object>> diff = new LinkedHashMap<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            Map<String, Object> entry = new LinkedHashMap<>();

            if (!data1.containsKey(key)) {
                entry.put("status", "added");
                entry.put("value", value2);
            } else if (!data2.containsKey(key)) {
                entry.put("status", "removed");
                entry.put("value", value1);
            } else if (Objects.equals(value1, value2)) {
                entry.put("status", "unchanged");
                entry.put("value", value1);
            } else {
                entry.put("status", "updated");
                entry.put("oldValue", value1);
                entry.put("newValue", value2);
            }
            diff.put(key, entry);
        }
        return diff;
    }
}
