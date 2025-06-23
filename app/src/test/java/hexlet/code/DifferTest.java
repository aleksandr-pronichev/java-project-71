package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        String actual = Differ.generate(data1, data2, "stylish");
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

        String actual = Differ.generate(data1, data2, "stylish");
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

        String actual = Differ.generate(data1, data2, "stylish");
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

        String actual = Differ.generate(data1, data2, "stylish");
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

        String actual = Differ.generate(data1, data2, "stylish");
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
        String actual = Differ.generate(data1, data2, "stylish");
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testPlainFormatter() throws Exception {
        String filepath1 = "src/test/resources/nested1.json";
        String filepath2 = "src/test/resources/nested2.json";
        Map<String, Object> data1 = (Map<String, Object>) Parser.parse(filepath1);
        Map<String, Object> data2 = (Map<String, Object>) Parser.parse(filepath2);
        String expected = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'
            """;
        String actual = Differ.generate(data1, data2, "plain");
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testJsonFormatterSimple() throws Exception {
        Map<String, Object> data1 = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        );
        Map<String, Object> data2 = Map.of(
                "timeout", 20,
                "verbose", true,
                "host", "hexlet.io"
        );

        String actualJson = hexlet.code.formatters.JsonFormatter.format(data1, data2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> actualMap = mapper.readValue(actualJson, Map.class);

        Map<String, Object> expectedMap = Map.of(
                "follow", Map.of("status", "removed", "value", false),
                "host", Map.of("status", "unchanged", "value", "hexlet.io"),
                "proxy", Map.of("status", "removed", "value", "123.234.53.22"),
                "timeout", Map.of("status", "updated", "oldValue", 50, "newValue", 20),
                "verbose", Map.of("status", "added", "value", true)
        );

        assertEquals(expectedMap, actualMap);
    }
}
