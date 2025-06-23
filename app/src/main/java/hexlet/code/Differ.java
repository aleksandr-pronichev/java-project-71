package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        return Formatter.format(format, data1, data2);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1 = (Map<String, Object>) Parser.parse(filepath1);
        Map<String, Object> data2 = (Map<String, Object>) Parser.parse(filepath2);
        return generate(data1, data2, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
