package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());
        List<String> lines = new ArrayList<>();
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                lines.add("  + " + key + ": " + value2); // только во втором
            } else if (!data2.containsKey(key)) {
                lines.add("  - " + key + ": " + value1); // только в первом
            } else if (Objects.equals(value1, value2)) {
                lines.add("    " + key + ": " + value1); // одинаковые
            } else {
                lines.add("  - " + key + ": " + value1); // изменён
                lines.add("  + " + key + ": " + value2);
            }
        }
        return "{\n" + String.join("\n", lines) + "\n}";
    }
}
