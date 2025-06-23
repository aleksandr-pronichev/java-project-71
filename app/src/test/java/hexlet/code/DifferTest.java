package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testEqualFlatJson() {
        Map<String, Object> data1 = Map.of("key1", "value1", "key2", 42);
        Map<String, Object> data2 = Map.of("key1", "value1", "key2", 42);

        String expected = """
                {
                    key1: value1
                    key2: 42
                }""";

        String actual = Differ.generate(data1, data2);
        assertEquals(expected.strip(), actual.strip());
    }

    @Test
    public void testAddedKey() {
        Map<String, Object> data1 = Map.of("key1", "value1");
        Map<String, Object> data2 = Map.of("key1", "value1", "key2", 100);

        String expected = """
                {
                    key1: value1
                  + key2: 100
                }""";

        String actual = Differ.generate(data1, data2);
        assertEquals(expected.strip(), actual.strip());
    }

    @Test
    public void testRemovedKey() {
        Map<String, Object> data1 = Map.of("key1", "value1", "key2", 100);
        Map<String, Object> data2 = Map.of("key1", "value1");

        String expected = """
                {
                    key1: value1
                  - key2: 100
                }""";

        String actual = Differ.generate(data1, data2);
        assertEquals(expected.strip(), actual.strip());
    }

    @Test
    public void testChangedValue() {
        Map<String, Object> data1 = Map.of("key1", "old");
        Map<String, Object> data2 = Map.of("key1", "new");

        String expected = """
                {
                  - key1: old
                  + key1: new
                }""";

        String actual = Differ.generate(data1, data2);
        assertEquals(expected.strip(), actual.strip());
    }

    @Test
    public void testMultipleChanges() {
        Map<String, Object> data1 = Map.of("host", "hexlet.io", "timeout", 50);
        Map<String, Object> data2 = Map.of("timeout", 20, "verbose", true, "host", "hexlet.io");

        String expected = """
                {
                    host: hexlet.io
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String actual = Differ.generate(data1, data2);
        assertEquals(expected.strip(), actual.strip());
    }

    @Test
    void testJsonNestedStylish() throws Exception {
        String filepath1 = "src/test/resources/nested1.json";
        String filepath2 = "src/test/resources/nested2.json";
        Map<String, Object> data1 = (Map<String, Object>) Parser.parse(filepath1);
        Map<String, Object> data2 = (Map<String, Object>) Parser.parse(filepath2);
        String expected = """
    {
        chars1: [a, b, c]
      - chars2: [d, e, f]
      + chars2: false
      - checked: false
      + checked: true
      - default: null
      + default: [value1, value2]
      - id: 45
      + id: null
      - key1: value1
      + key2: value2
        numbers1: [1, 2, 3, 4]
      - numbers2: [2, 3, 4, 5]
      + numbers2: [22, 33, 44, 55]
      - numbers3: [3, 4, 5]
      + numbers4: [4, 5, 6]
      + obj1: {nestedKey=value, isNested=true}
      - setting1: Some value
      + setting1: Another value
      - setting2: 200
      + setting2: 300
      - setting3: true
      + setting3: none
    }
    """;
        String actual = Differ.generate(data1, data2);
        assertEquals(expected.trim(), actual.trim());
    }
}
