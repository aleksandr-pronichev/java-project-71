package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppYamlTest {

    @Test
    public void testFlatYamlDiff() throws Exception {
        String path1 = "src/test/resources/file1.yaml";
        String path2 = "src/test/resources/file2.yaml";

        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        String result = Differ.generateDiff(path1, path2, "stylish");
        assertEquals(expected.strip(), result.strip());
    }
}

