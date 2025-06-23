package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;

import java.util.Map;

public class Formatter {
    public static String format(String format, Map<String, Object> data1, Map<String, Object> data2) {
        return switch (format) {
            case "plain" -> PlainFormatter.format(data1, data2);
            case "stylish" -> StylishFormatter.format(data1, data2);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
