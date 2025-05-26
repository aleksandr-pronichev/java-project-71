package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import hexlet.code.Parser;

import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public String call() {
        try {
            Map<String, Object> data1 = (Map<String, Object>) Parser.parse(filepath1);
            Map<String, Object> data2 = (Map<String, Object>) Parser.parse(filepath2);
            return Differ.generate(data1, data2);
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String result = CommandLine.call(new App(), args);
        System.out.println(result);
    }
}
