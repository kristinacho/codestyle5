/**
 * Задание 1.4. Изменение сущности Дробь.
 * Измените сущность Дробь из задачи 1.5.5 с учётом следующих требований:
 * - Дробь должна быть неизменяемой после создания.
 * - Необходимо корректно обрабатывать отрицательные значения.
 *   При этом знаменатель не может быть отрицательным.
 * Для проверки работоспособности решения продемонстрируйте его на примерах.
 */

package ru.Beklemysheva.fraction;

/**
 * Класс для работы с дробями.
 * Поддерживает основные арифметические операции: сложение, вычитание, умножение и деление.
 * Автоматически сокращает дроби и нормализует знаменатель.
 */
public final class Fraction {
    private final int numerator;
    private final int denominator;

    /**
     * Создает новую дробь с указанными числителем и знаменателем.
     * Знаменатель не может быть нулем.
     * Если знаменатель отрицательный, нормализует дробь (переносит минус в числитель).
     *
     * @param numerator числитель дроби
     * @param denominator знаменатель дроби (не может быть нулем)
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            System.out.println("Ошибка: знаменатель не может быть нулем");
            this.numerator = 0;
            this.denominator = 1;
            return;
        }

        if (denominator < 0) {
            this.numerator = -numerator;
            this.denominator = -denominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    /**
     * Складывает текущую дробь с другой дробью.
     *
     * @param other дробь для сложения (не может быть null)
     * @return новая дробь - результат сложения или null при ошибке
     */
    public Fraction add(Fraction other) {
        if (other == null) {
            System.out.println("Ошибка: дробь для сложения не может быть null");
            return null;
        }
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).reduce();
    }

    /**
     * Вычитает другую дробь из текущей дроби.
     *
     * @param other дробь для вычитания (не может быть null)
     * @return новая дробь - результат вычитания или null при ошибке
     */
    public Fraction subtract(Fraction other) {
        if (other == null) {
            System.out.println("Ошибка: дробь для вычитания не может быть null");
            return null;
        }
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).reduce();
    }

    /**
     * Умножает текущую дробь на другую дробь.
     *
     * @param other дробь для умножения (не может быть null)
     * @return новая дробь - результат умножения или null при ошибке
     */
    public Fraction multiply(Fraction other) {
        if (other == null) {
            System.out.println("Ошибка: дробь для умножения не может быть null");
            return null;
        }
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator).reduce();
    }

    /**
     * Делит текущую дробь на другую дробь.
     *
     * @param other дробь-делитель (не может быть null или нулем)
     * @return новая дробь - результат деления или null при ошибке
     */
    public Fraction divide(Fraction other) {
        if (other == null) {
            System.out.println("Ошибка: дробь для деления не может быть null");
            return null;
        }
        if (other.numerator == 0) {
            System.out.println("Ошибка: деление на ноль невозможно");
            return null;
        }
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator).reduce();
    }

    /**
     * Сокращает дробь до несократимого вида.
     *
     * @return новая сокращенная дробь
     */
    private Fraction reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    /**
     * Вычисляет наибольший общий делитель двух чисел.
     *
     * @param a первое число
     * @param b второе число
     * @return наибольший общий делитель
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Возвращает строковое представление дроби.
     * Если числитель равен 0, возвращает "0".
     *
     * @return строковое представление дроби
     */
    @Override
    public String toString() {
        if (numerator == 0) {
            return "0";
        }
        return numerator + "/" + denominator;
    }
}