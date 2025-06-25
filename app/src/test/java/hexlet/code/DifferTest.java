package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {

    public static String getNormalizedFileString(String filePath) throws Exception {
        Path absPath = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(absPath).trim().replaceAll("\\r", "");
    }

    // json -> stylish
    @Test
    void testJsonInputStylishOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.json";
        String file2 = "src/test/resources/input/file2.json";
        String expected = getNormalizedFileString("src/test/resources/expected/stylish.txt");

        String actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expected, actual);
    }

    // json -> plain
    @Test
    void testJsonInputPlainOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.json";
        String file2 = "src/test/resources/input/file2.json";
        String expected = getNormalizedFileString("src/test/resources/expected/plain.txt");

        String actual = Differ.generate(file1, file2, "plain");
        assertEquals(expected, actual);
    }

    // json -> json
    @Test
    void testJsonInputJsonOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.json";
        String file2 = "src/test/resources/input/file2.json";
        String expected = getNormalizedFileString("src/test/resources/expected/json.txt");

        String actual = Differ.generate(file1, file2, "json");

        ObjectMapper mapper = new ObjectMapper();
        var expectedMap = mapper.readValue(expected, Map.class);
        var actualMap = mapper.readValue(actual, Map.class);

        assertEquals(expectedMap, actualMap);
    }

    // json -> default
    @Test
    void testJsonInputDefaultOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.json";
        String file2 = "src/test/resources/input/file2.json";
        String expected = getNormalizedFileString("src/test/resources/expected/default.txt");

        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }

    // yml -> stylish
    @Test
    void testYamlInputStylishOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.yaml";
        String file2 = "src/test/resources/input/file2.yaml";
        String expected = getNormalizedFileString("src/test/resources/expected/stylish.txt");

        String actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expected, actual);
    }

    // yml -> plain
    @Test
    void testYamlInputPlainOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.yaml";
        String file2 = "src/test/resources/input/file2.yaml";
        String expected = getNormalizedFileString("src/test/resources/expected/plain.txt");

        String actual = Differ.generate(file1, file2, "plain");
        assertEquals(expected, actual);
    }

    // yml -> json
    @Test
    void testYamlInputJsonOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.yaml";
        String file2 = "src/test/resources/input/file2.yaml";
        String expected = getNormalizedFileString("src/test/resources/expected/json.txt");

        String actual = Differ.generate(file1, file2, "json");

        ObjectMapper mapper = new ObjectMapper();
        var expectedMap = mapper.readValue(expected, Map.class);
        var actualMap = mapper.readValue(actual, Map.class);

        assertEquals(expectedMap, actualMap);
    }

    // yml -> default
    @Test
    void testYamlInputDefaultOutput() throws Exception {
        String file1 = "src/test/resources/input/file1.yaml";
        String file2 = "src/test/resources/input/file2.yaml";
        String expected = getNormalizedFileString("src/test/resources/expected/default.txt");

        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }
}
