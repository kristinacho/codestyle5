/**
 * Задание 4.2. Дробь это число.
 * Измените сущность Дробь, полученную в задаче 2.3.1. Дробь должна быть подтипом класса
 * Number. Данный класс входит в стандартную редакцию языка Java.
 */
 package ru.Beklemysheva.fraction;

import java.util.Objects;

/**
 * Класс, представляющий математическую дробь как подтип {@link Number}.
 */
public class NumberFraction extends Number {
    /** Числитель дроби */
    private int numerator;

    /** Знаменатель дроби (всегда положительное число) */
    private int denominator;

    /**
     * Создает новый экземпляр дроби.
     * @param numerator числитель дроби
     * @param denominator знаменатель дроби (не может быть 0)
     */
    public NumberFraction(int numerator, int denominator) {
        if (denominator == 0) {
            System.out.println("Error: Denominator cannot be zero. Set to 1.");
            this.denominator = 1;
        } else {
            this.denominator = Math.abs(denominator);
        }

        // Корректировка знаков: знаменатель всегда положительный
        if (denominator < 0) {
            this.numerator = -numerator;
        } else {
            this.numerator = numerator;
        }

        reduce();
    }

    /**
     * Сокращает дробь путем деления числителя и знаменателя на их НОД.
     */
    private void reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * Вычисляет наибольший общий делитель двух чисел (алгоритм Евклида).
     * @param a первое число
     * @param b второе число
     * @return НОД(a, b)
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Складывает текущую дробь с другой дробью.
     * @param other дробь для сложения
     * @return новая дробь - результат сложения
     */
    public NumberFraction add(NumberFraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new NumberFraction(newNumerator, newDenominator);
    }

    /**
     * Вычитает другую дробь из текущей дроби.
     * @param other дробь для вычитания
     * @return новая дробь - результат вычитания
     */
    public NumberFraction subtract(NumberFraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new NumberFraction(newNumerator, newDenominator);
    }

    /**
     * Умножает текущую дробь на другую дробь.
     * @param other дробь для умножения
     * @return новая дробь - результат умножения
     */
    public NumberFraction multiply(NumberFraction other) {
        return new NumberFraction(this.numerator * other.numerator,
                this.denominator * other.denominator);
    }

    /**
     * Делит текущую дробь на другую дробь.
     * @param other дробь-делитель
     * @return новая дробь - результат деления
     */
    public NumberFraction divide(NumberFraction other) {
        if (other.numerator == 0) {
            System.out.println("Error: Division by zero is not allowed. Returning 0.");
            return new NumberFraction(0, 1);
        }
        return new NumberFraction(this.numerator * other.denominator,
                this.denominator * other.numerator);
    }

    /**
     * Возвращает целочисленное значение дроби (с округлением в сторону нуля).
     * @return целая часть дроби
     */
    @Override
    public int intValue() {
        return numerator / denominator;
    }

    /**
     * Возвращает длинное целочисленное значение дроби.
     * @return дробь как long
     */
    @Override
    public long longValue() {
        return (long) numerator / denominator;
    }

    /**
     * Возвращает значение дроби как float.
     * @return дробь как float
     */
    @Override
    public float floatValue() {
        return (float) numerator / denominator;
    }

    /**
     * Возвращает значение дроби как double.
     * @return дробь как double
     */
    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    /**
     * Возвращает строковое представление дроби.
     * @return строка в формате "числитель/знаменатель"
     */
    @Override
    public String toString() {
        if (numerator == 0) {
            return "0";
        }
        return numerator + "/" + denominator;
    }

    /**
     * Сравнивает дроби на равенство.
     * @param o объект для сравнения
     * @return true если дроби равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberFraction that = (NumberFraction) o;
        return numerator == that.numerator &&
                denominator == that.denominator;
    }

    /**
     * Возвращает хэш-код дроби.
     * @return хэш-код, вычисленный на основе числителя и знаменателя
     */
    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}