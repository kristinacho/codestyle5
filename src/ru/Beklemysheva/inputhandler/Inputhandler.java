/* Глобальный обработчик входных значений */
package ru.Beklemysheva.inputhandler;

import ru.Beklemysheva.inputhandler.config.DoubleInputConfig;
import ru.Beklemysheva.inputhandler.config.FileCheckConfig;
import ru.Beklemysheva.inputhandler.config.IntInputConfig;
import ru.Beklemysheva.inputhandler.config.StringInputConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Класс InputHandler предоставляет методы для обработки и валидации различных типов входных данных.
 */
public class Inputhandler {

    /**
     * Защищенный конструктор для предотвращения создания экземпляров класса.
     */
    protected Inputhandler() {}

    /**
     * Отображает подсказку для ввода пользователя.
     *
     * @param prompt Подсказка для отображения.
     * @param formatHint Подсказка формата для отображения.
     */
    public static void showPrompt(String prompt, String formatHint) {
        if (formatHint != null && !formatHint.isEmpty()) {
            System.out.println("Format: " + formatHint);
        }
        System.out.print(prompt.isEmpty() ? "> " : prompt);
    }

    /**
     * Получает и возвращает обрезанный ввод пользователя.
     *
     * @return Обрезанный ввод пользователя.
     */
    private static String getTrimmedInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            return input != null ? input.trim() : "";
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Получает и возвращает целое число от пользователя на основе заданной конфигурации.
     *
     * @param config Конфигурация для ввода целого числа.
     * @return Целое число, введенное пользователем.
     */
    public static int getInt(IntInputConfig config) {
        while (true) {
            showPrompt(config.getPrompt(), config.getFormatHint());
            String input = getTrimmedInput();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            try {
                int value = Integer.parseInt(input);

                if (config.getMin() != null && value < config.getMin()) {
                    String error = config.getErrorMsg().isEmpty()
                            ? "Value must be >= " + config.getMin()
                            : config.getErrorMsg();
                    System.out.println(error);
                    continue;
                }

                if (config.getMax() != null && value > config.getMax()) {
                    String error = config.getErrorMsg().isEmpty()
                            ? "Value must be <= " + config.getMax()
                            : config.getErrorMsg();
                    System.out.println(error);
                    continue;
                }

                if (!config.getAllowedValues().isEmpty()
                        && !config.getAllowedValues().contains(value)) {
                    System.out.print("Allowed values: ");
                    config.getAllowedValues().forEach(v -> System.out.print(v + " "));
                    System.out.println();
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                String error = config.getErrorMsg().isEmpty()
                        ? "Invalid integer input."
                        : config.getErrorMsg();
                System.out.println(error);
            }
        }
    }

    /**
     * Получает и возвращает число с плавающей запятой от пользователя на основе заданной конфигурации.
     * @param config Конфигурация для ввода числа с плавающей запятой.
     * @return Число с плавающей запятой, введенное пользователем.
     */
    public static double getDouble(DoubleInputConfig config) {
        while (true) {
            showPrompt(config.getPrompt(), config.getFormatHint());
            String input = getTrimmedInput();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            try {
                double value = Double.parseDouble(input);

                if (config.getMin() != null && value < config.getMin()) {
                    String error = config.getErrorMsg().isEmpty()
                            ? "Value must be >= " + config.getMin()
                            : config.getErrorMsg();
                    System.out.println(error);
                    continue;
                }

                if (config.getMax() != null && value > config.getMax()) {
                    String error = config.getErrorMsg().isEmpty()
                            ? "Value must be <= " + config.getMax()
                            : config.getErrorMsg();
                    System.out.println(error);
                    continue;
                }

                if (!config.getAllowedValues().isEmpty()
                        && !config.getAllowedValues().contains(value)) {
                    System.out.print("Allowed values: ");
                    config.getAllowedValues().forEach(v -> System.out.print(v + " "));
                    System.out.println();
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                String error = config.getErrorMsg().isEmpty()
                        ? "Invalid number input."
                        : config.getErrorMsg();
                System.out.println(error);
            }
        }
    }

    /**
     * Получает и возвращает строку от пользователя на основе заданной конфигурации.
     * @param config Конфигурация для ввода строки.
     * @return Строка, введенная пользователем.
     */
    public static String getString(StringInputConfig config) {
        while (true) {
            showPrompt(config.getPrompt(), config.getFormatHint());
            String input = getTrimmedInput();

            if (input.isEmpty() && config.getMinLength() != null && config.getMinLength() > 0) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            int length = input.length();
            if (config.getMinLength() != null && length < config.getMinLength()) {
                String error = config.getErrorMsg().isEmpty()
                        ? "Input must be at least " + config.getMinLength() + " characters."
                        : config.getErrorMsg();
                System.out.println(error);
                continue;
            }

            if (config.getMaxLength() != null && length > config.getMaxLength()) {
                String error = config.getErrorMsg().isEmpty()
                        ? "Input must be no more than " + config.getMaxLength() + " characters."
                        : config.getErrorMsg();
                System.out.println(error);
                continue;
            }

            if (!config.getAllowedValues().isEmpty()
                    && !config.getAllowedValues().contains(input)) {
                System.out.print("Allowed values: ");
                config.getAllowedValues().forEach(v -> System.out.print(v + " "));
                System.out.println();
                continue;
            }

            if (config.isRequireUppercaseFirstLetter() && !input.isEmpty()) {
                if (!Character.isUpperCase(input.charAt(0))) {
                    System.out.println("Input must start with an uppercase letter.");
                    continue;
                }
            }

            if (config.isAllowOnlyLettersAndSpaces()) {
                if (!input.matches("[a-zA-Zа-яА-ЯёЁ ]*")) {
                    System.out.println("Input contains invalid characters. Only letters and spaces are allowed.");
                    continue;
                }
            }

            return input;
        }
    }

    /**
     * Получает и возвращает булево значение от пользователя.
     *
     * @param prompt Подсказка для отображения.
     * @return Булево значение, введенное пользователем.
     */
    public static boolean getBoolean(String prompt) {
        while (true) {
            showPrompt(prompt, "");
            String input = getTrimmedInput().toLowerCase();

            switch (input) {
                case "y":
                case "yes":
                case "1":
                case "true":
                    return true;
                case "n":
                case "no":
                case "0":
                case "false":
                    return false;
                default:
                    System.out.println("Please enter 'y' or 'n'.");
            }
        }
    }

    /**
     * Получает и возвращает путь к файлу от пользователя на основе заданной конфигурации.
     *
     * @param config Конфигурация для проверки файла.
     * @return Путь к файлу, введенный пользователем.
     */
    public static Path getFilePath(FileCheckConfig config) {
        while (true) {
            System.out.print("Input file path: ");
            String input = getTrimmedInput();
            Path path = Paths.get(input);

            if (checkFileValidity(path, config)) {
                return path;
            }
        }
    }

    /**
     * Проверяет валидность файла на основе заданной конфигурации.
     *
     * @param path Путь к файлу.
     * @param config Конфигурация для проверки файла.
     * @return true, если файл валиден, иначе false.
     */
    private static boolean checkFileValidity(Path path, FileCheckConfig config) {
        if (config.isCheckExistence() && !Files.exists(path)) {
            String error = config.getErrorMsg().isEmpty()
                    ? "File doesn't exist: " + path
                    : config.getErrorMsg();
            System.out.println(error);
            return false;
        }

        if (config.isCheckExtension() && !config.getAllowedExtensions().isEmpty()) {
            String fileName = path.getFileName().toString();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex == -1) {
                System.out.println("File has no extension.");
                return false;
            }

            String ext = fileName.substring(dotIndex).toLowerCase();
            boolean valid = config.getAllowedExtensions().stream()
                    .anyMatch(e -> ext.equals(e.toLowerCase()));

            if (!valid) {
                System.out.print("Allowed extensions: ");
                config.getAllowedExtensions().forEach(e -> System.out.print(e + " "));
                System.out.println();
                return false;
            }
        }
        return true;
    }
}