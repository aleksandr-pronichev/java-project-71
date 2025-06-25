package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        // 1. Читаем файлы и формат
        String content1 = Files.readString(Paths.get(filepath1));
        String content2 = Files.readString(Paths.get(filepath2));
        String type1 = getDataFormat(filepath1).toLowerCase();
        String type2 = getDataFormat(filepath2).toLowerCase();

        // 2. Парсим данные
        Map<String, Object> data1 = Parser.parse(content1, type1);
        Map<String, Object> data2 = Parser.parse(content2, type2);

        // 3. Построение разницы
        Map<String, Map<String, Object>> diff = DiffBuilder.build(data1, data2);

        // 4. Возвращаем форматированные данные
        return Formatter.format(format, diff);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generateDiff(String filepath1, String filepath2, String format) throws Exception {
        return generate(filepath1, filepath2, format);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
