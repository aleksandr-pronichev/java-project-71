package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String format) {
        return Formatter.format(format, data1, data2);
    }
}
