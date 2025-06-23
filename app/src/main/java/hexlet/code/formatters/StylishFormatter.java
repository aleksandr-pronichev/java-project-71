package hexlet.code.formatters;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class StylishFormatter {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());
        List<String> lines = new ArrayList<>();
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                lines.add("  + " + key + ": " + value2);
            } else if (!data2.containsKey(key)) {
                lines.add("  - " + key + ": " + value1);
            } else if (Objects.equals(value1, value2)) {
                lines.add("    " + key + ": " + value1);
            } else {
                lines.add("  - " + key + ": " + value1);
                lines.add("  + " + key + ": " + value2);
            }
        }
        return "{\n" + String.join("\n", lines) + "\n}";
    }
}
