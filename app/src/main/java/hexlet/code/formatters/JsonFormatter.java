package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, Map<String, Object>> diff) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("JsonFormatter error", e);
        }
    }
}
