package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;

import java.util.Map;

public class Formatter {
    public static String format(String format, Map<String, Map<String, Object>> diff) throws Exception {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
