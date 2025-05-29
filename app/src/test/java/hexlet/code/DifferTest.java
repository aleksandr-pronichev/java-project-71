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
}
