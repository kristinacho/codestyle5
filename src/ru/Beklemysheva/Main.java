package ru.Beklemysheva;

import ru.Beklemysheva.fraction.Fraction;
import ru.Beklemysheva.fraction.NumberFraction;
import ru.Beklemysheva.inputhandler.Inputhandler;
import ru.Beklemysheva.inputhandler.config.IntInputConfig;
import ru.Beklemysheva.inputhandler.config.DoubleInputConfig;
import ru.Beklemysheva.inputhandler.config.StringInputConfig;
import ru.Beklemysheva.city.City;
import ru.Beklemysheva.city.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        demonstrateFractionOperations();
        demonstrateNumberAddition();
        handleCityTask();
        demonstratePathTask();
        demonstrateForbiddenFraction();
        demonstrateFractionAsNumber();
    }
    private static void demonstrateFractionOperations() {
        System.out.println("№1.4. Дроби");
        Fraction fraction1 = inputFraction("первую");
        Fraction fraction2 = inputFraction("вторую");
        System.out.println("\nРезультаты операций:");
        printFractionOperation("Сумма", fraction1.add(fraction2));
        printFractionOperation("Разность", fraction1.subtract(fraction2));
        printFractionOperation("Произведение", fraction1.multiply(fraction2));
        printFractionOperation("Частное", fraction1.divide(fraction2));
    }

    private static void demonstrateNumberAddition() {
        System.out.println("\n№5.1.Сложение чисел");
        calculateAndPrintSum(3);
    }

    private static void handleCityTask() {
        System.out.println("\n№1.10.Города");

        List<City> cities = new ArrayList<>();

        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");
        City F = new City("F");

        cities.add(A);
        cities.add(B);
        cities.add(C);
        cities.add(D);
        cities.add(E);
        cities.add(F);

        A.addRoute(B, 5);
        A.addRoute(D, 6);
        A.addRoute(F, 1);
        B.addRoute(F, 1);
        B.addRoute(C, 3);
        C.addRoute(D, 4);
        D.addRoute(E, 2);
        E.addRoute(F, 2);

        StringInputConfig cityNameConfig = new StringInputConfig.Builder()
                .setPrompt("Введите название нового города: ")
                .setMinLength(1)
                .setMaxLength(50)
                .build();

        String cityName = Inputhandler.getString(cityNameConfig);
        City newCity = new City(cityName);
        cities.add(newCity);

        while (true) {
            StringInputConfig yesNoConfig = new StringInputConfig.Builder()
                    .setPrompt("Добавить маршрут? (y/n): ")
                    .setAllowedValues(Arrays.asList("y", "n"))
                    .build();

            String answer = Inputhandler.getString(yesNoConfig);
            if (answer.equalsIgnoreCase("n")) {
                break;
            }

            StringInputConfig destNameConfig = new StringInputConfig.Builder()
                    .setPrompt("Введите название города назначения: ")
                    .setMinLength(1)
                    .setMaxLength(50)
                    .build();

            String destName = Inputhandler.getString(destNameConfig);

            City destCity = cities.stream()
                    .filter(c -> c.getName().equalsIgnoreCase(destName))
                    .findFirst()
                    .orElse(null);

            if (destCity == null) {
                System.out.println("Город " + destName + " не найден!");
                continue;
            }

            DoubleInputConfig costConfig = new DoubleInputConfig.Builder()
                    .setPrompt("Введите стоимость поездки (больше 0): ")
                    .setMin(0.01)
                    .build();

            double cost = Inputhandler.getDouble(costConfig);

            newCity.addRoute(destCity, cost);
            destCity.addRoute(newCity, cost);
        }

        System.out.println("\nОбновленная карта городов:");
        cities.forEach(System.out::println);
    }

    private static void demonstratePathTask() {
        System.out.println("\n№2.5. Задание 2.5 - Маршрут");

        City A = new City("A");
        City B = new City("B");
        City C = new City("C");
        City D = new City("D");
        City E = new City("E");
        City F = new City("F");

        A.addRoute(B, 5);
        B.addRoute(A, 5);

        A.addRoute(D, 6);
        D.addRoute(A, 6);

        A.addRoute(F, 1);
        F.addRoute(A, 1);

        B.addRoute(F, 1);
        F.addRoute(B, 1);

        B.addRoute(C, 3);
        C.addRoute(B, 3);

        C.addRoute(D, 4);
        D.addRoute(C, 4);

        D.addRoute(E, 2);
        E.addRoute(D, 2);

        E.addRoute(F, 2);
        F.addRoute(E, 2);

        if (F == null || D == null) {
            System.out.println("Ошибка: один из городов не создан!");
            return;
        }

        Path path = new Path(F, D);
        if (path.getStart() != null && path.getEnd() != null) {
            System.out.println("Маршрут из F в D: " + path);
        } else {
            System.out.println("Не удалось создать маршрут из-за ошибки в городах");
        }

        System.out.println("\n№6.5. Сравнение городов");

        City X = new City("X");
        City Y = new City("Y");
        X.addRoute(A, 1);
        Y.addRoute(A, 2);

        System.out.println("Города X и Y равны? " + X.equals(Y));

        Y.addRoute(B, 3);
        System.out.println("Города X и Y равны после изменения? " + X.equals(Y));

    }

    private static Fraction inputFraction(String fractionName) {
        System.out.println("\nВведите " + fractionName + " дробь:");
        int numerator = Inputhandler.getInt(new IntInputConfig.Builder()
                .setPrompt("Числитель: ")
                .build());
        int denominator = getValidDenominator();
        return new Fraction(numerator, denominator);
    }

    private static int getValidDenominator() {
        while (true) {
            int denominator = Inputhandler.getInt(new IntInputConfig.Builder()
                    .setPrompt("Знаменатель: ")
                    .build());
            if (denominator != 0) return denominator;
            System.out.println("Ошибка: знаменатель не может быть 0!");
        }
    }

    private static void printFractionOperation(String operationName, Fraction result) {
        System.out.println(operationName + ": " + result);
    }

    private static void calculateAndPrintSum(int count) {
        List<Double> numbers = inputNumbers(count);
        double result = calculateSum(numbers);
        System.out.println("Результат: " + result);
    }

    private static double calculateSum(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).sum();
    }

    private static List<Double> inputNumbers(int count) {
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(inputNumber(i + 1));
        }
        return numbers;
    }

    private static double inputNumber(int index) {
        return Inputhandler.getDouble(
                new DoubleInputConfig.Builder()
                        .setPrompt("Введите число " + index + ": ")
                        .setFormatHint("Вещественное число")
                        .setErrorMsg("Некорректный ввод. Пожалуйста, введите число.")
                        .build()
        );
    }


    private static void demonstrateForbiddenFraction() {
        System.out.println("\n№3.1. Запретная дробь");

        Fraction f1 = new Fraction(3, 4);
        Fraction f2 = new Fraction(-2, 5);
        Fraction f3 = new Fraction(4, -6);
        Fraction f4 = new Fraction(0, 1);

        System.out.println("Дробь 1: " + f1);
        System.out.println("Дробь 2: " + f2);
        System.out.println("Дробь 3 (изначально 4/-6): " + f3);
        System.out.println("Дробь 4: " + f4);

        System.out.println("\nАрифметические операции:");
        System.out.println(f1 + " + " + f2 + " = " + f1.add(f2));
        System.out.println(f1 + " - " + f3 + " = " + f1.subtract(f3));
        System.out.println(f2 + " * " + f3 + " = " + f2.multiply(f3));
        System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));

    }

    private static void demonstrateFractionAsNumber() {
        System.out.println("\n№4.2. Дробь это число");

        NumberFraction f1 = inputNumberFraction("первую");
        NumberFraction f2 = inputNumberFraction("вторую");

        System.out.println("\nРезультаты операций как Number:");
        System.out.println("Сумма (double): " + f1.add(f2).doubleValue());
        System.out.println("Разность (int): " + f1.subtract(f2).intValue());
        System.out.println("Произведение (float): " + f1.multiply(f2).floatValue());
        System.out.println("Частное (long): " + f1.divide(f2).longValue());

        Number num = f1;
        System.out.println("Дробь как Number: " + num.doubleValue());
    }

    private static NumberFraction inputNumberFraction(String fractionName) {
        System.out.println("\nВведите " + fractionName + " дробь:");
        int numerator = Inputhandler.getInt(new IntInputConfig.Builder()
                .setPrompt("Числитель: ")
                .build());
        int denominator = getValidDenominator();
        return new NumberFraction(numerator, denominator);
    }

}