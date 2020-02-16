import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class OurFileReader {

    private static File file = new File("./src/main/resources/file");
    private static File file1 = new File("./src/main/resources/file2");
    private static File file2 = new File("./src/main/resources/text");

    private static void count(File file) throws IOException {
        Scanner scanner = new Scanner(Paths.get(file.toString()), StandardCharsets.UTF_8.name());

        String data = scanner.useDelimiter("\\A").next();

        String string = data
                .replaceAll("\\p{Punct}+", "")  // Тил
                .replaceAll("\\)", "")
                .replaceAll("\\(", "")
//                .replaceAll("\\s", " ")
                .replaceAll("\\{", "")
                .replaceAll("}", "")
                .replaceAll("—", "")
                .replaceAll("'?'", "")
                .replaceAll(",", "")
                .replaceAll("%", "")   // Процент
                .replaceAll("\t", "")  // Табуляция (заменяем на пробел)
                .replaceAll("\n", "")  // Переход строки (заменяем на пробел)
                .replaceAll("\r", "")  // Возврат каретки (заменяем на пробел)
                .replaceAll("!", "")   // Восклицательный знак
                .replaceAll("\"", "")  // Двойная кавычка
                .replaceAll("#", "")   // Октоторп, решетка
                .replaceAll("\\$", "") // Знак доллара
                .replaceAll("&", "")   // Амперсанд
                .replaceAll("'", "")   // Одиночная кавычка
                .replaceAll("\\(", "") // Открывающаяся скобка
                .replaceAll("\\)", "") // Закрывающаяся скобка
                .replaceAll("\\*", "") // Звездочка
                .replaceAll("\\+", "") // Знак плюс
                .replaceAll(",", "")   // Запятая
                .replaceAll("-", "")   // Дефис
                .replaceAll("\\.", "") // Точка
                .replaceAll("/", "")   // Слеш, косая черта
                .replaceAll(":", "")   // Двоеточие
                .replaceAll(";", "")   // Точка с запятой
                .replaceAll("<", "")   // Открывающаяся угловая скобка
                .replaceAll("=", "")   // Знак равно
                .replaceAll(">", "")   // Закрывающаяся угловая скобка
                .replaceAll("\\?", "") // Вопросительный знак
                .replaceAll("@", "")   // At sign, по цене, собачка
                .replaceAll("\\[", "") // Открывающаяся квадратная скобка
                .replaceAll("\\\\", "") // Одиночный обратный слеш '\'
                .replaceAll("]", "") // Закрывающаяся квадратная скобка
                .replaceAll("\\^", "") // Циркумфлекс
                .replaceAll("_", "")   // Нижнее подчеркивание
                .replaceAll("`", "")   // Гравис
                .replaceAll("\\{", "") // Открывающаяся фигурная скобка
                .replaceAll("\\|", "") // Вертикальная черта
                .replaceAll("}", "") // Закрывающаяся фигурная скобка
                .toLowerCase()
                .replaceAll("\\s+", " ")
                .replaceAll("~", "")
                .trim();

        scanner.close();


        String[] strings = string.split(" ");
        Map<String, Integer> map = new HashMap<>();

        Arrays.sort(strings);
        for (String s : strings) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        int max = Collections.max(map.values());
        String maxString = "";

        for (Map.Entry<String, Integer> a : map.entrySet()) {
            if (a.getValue().equals(max)) {
                maxString = a.getKey();
            }
        }

        System.out.printf("MAX STRING: %s \nREPEATED %d" +
                "\n________\n", maxString, max);

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите число от 1 до 3");
        int num = Integer.parseInt(reader.readLine());
        switch (num) {
            case 1:
                count(file);
                break;
            case 2:
                count(file1);
                break;
            case 3:
                count(file2);
                break;
        }
    }
}


