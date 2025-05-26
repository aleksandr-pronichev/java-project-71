package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    public static Object parse(String path) throws Exception {
        Path writeFilePath = Paths.get(path);
        String content = Files.readString(writeFilePath);

        if (path.endsWith(".json")) {
            var result = new ObjectMapper().readValue(content, Map.class);
            return result;
        } else {
            throw new Exception("Unsupported file format: " + path);
        }
    }
}
