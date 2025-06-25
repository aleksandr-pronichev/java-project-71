package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, Map<String, Object>> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}
