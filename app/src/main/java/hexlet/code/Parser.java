package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String data, String format) throws Exception {
        String normalizedFormat = format.trim().toLowerCase();
        if (normalizedFormat.equals("yml")) {
            normalizedFormat = "yaml";
        }
        switch (normalizedFormat) {
            case "json":
                return parseJson(data);
            case "yaml":
                return parseYaml(data);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    private static Map<String, Object> parseJson(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, Map.class);
    }

    private static Map<String, Object> parseYaml(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(data, Map.class);
    }
}
