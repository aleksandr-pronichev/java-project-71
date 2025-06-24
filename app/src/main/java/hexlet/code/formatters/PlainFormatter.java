package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(Map<String, Map<String, Object>> diff) {
        List<String> lines = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> meta = entry.getValue();
            String status = (String) meta.get("status");

            switch (status) {
                case "added" -> lines.add("Property '" + key + "' was added with value: "
                        + stringify(meta.get("value")));
                case "removed" -> lines.add("Property '" + key + "' was removed");
                case "updated" -> lines.add("Property '" + key + "' was updated. From "
                        + stringify(meta.get("oldValue")) + " to " + stringify(meta.get("newValue")));
                default -> {

                }
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
