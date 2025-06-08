package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {

    public static Object parse(String path) throws Exception {
        Path writeFilePath = Paths.get(path);
        String content = Files.readString(writeFilePath);

        if (path.endsWith(".json")) {
            return new ObjectMapper().readValue(content, Map.class);
        } else if (path.endsWith(".yaml") || path.endsWith(".yml")){
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            return yamlMapper.readValue(content, Map.class);
        } else {
            throw new Exception("Unsupported file format: " + path);
        }
    }
}
