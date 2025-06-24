package hexlet.code.formatters;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, Map<String, Object>> diff) {
        List<String> lines = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> meta = entry.getValue();
            String status = (String) meta.get("status");

            switch (status) {
                case "added" -> lines.add("  + " + key + ": " + stringify(meta.get("value")));
                case "removed" -> lines.add("  - " + key + ": " + stringify(meta.get("value")));
                case "unchanged" -> lines.add("    " + key + ": " + stringify(meta.get("value")));
                case "updated" -> {
                    lines.add("  - " + key + ": " + stringify(meta.get("oldValue")));
                    lines.add("  + " + key + ": " + stringify(meta.get("newValue")));
                }
            }
        }

        return "{\n" + String.join("\n", lines) + "\n}";
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return value.toString();
        }
        if (value instanceof String) {
            return value.toString();
        }
        return value.toString();
    }
}
