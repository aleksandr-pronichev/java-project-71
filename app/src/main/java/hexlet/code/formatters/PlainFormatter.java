package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class PlainFormatter {
    public static String format(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());
        List<String> lines = new ArrayList<>();

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (!inFirst && inSecond) {
                lines.add("Property '" + key + "' was added with value: " + stringify(value2));
            } else if (inFirst && !inSecond) {
                lines.add("Property '" + key + "' was removed");
            } else if (!Objects.equals(value1, value2)) {
                lines.add("Property '" + key + "' was updated. From " + stringify(value1) + " to " + stringify(value2));
            }
        }
        return String.join("\n", lines);
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
