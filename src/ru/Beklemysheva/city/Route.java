/**
 * Задание 1.10. Дороги.
 * Измените сущности из задачи 1.3.3. Гарантируйте, что между двумя городами может быть только
 * одна прямая дорога (другой путь может быть проложен только транзитом через другие города).
 * Задание 6.5. Сравнение городов.
 * Измените сущность Город, полученную в задаче 2.1.10. Переопределите метод сравнения
 * объектов по состоянию таким образом, чтобы два Города считались одинаковыми тогда, когда у
 * них одинаковый набор путей в другие города.
 */
package ru.Beklemysheva.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс представляет маршрут между городами с указанием стоимости пути.
 */
public class Route {
    private final City destination;

    private final double cost;

    /**
     * Создает новый маршрут между городами.
     * @param destination город назначения (не должен быть null)
     * @param cost стоимость проезда (должна быть > 0)
     */
    public Route(City destination, double cost) {
        if (destination == null) {
            System.out.println("Ошибка: город назначения не может быть null.");
            this.destination = new City("Неизвестный город");
        } else {
            this.destination = destination;
        }

        if (cost <= 0) {
            System.out.println("Ошибка: стоимость должна быть положительной.");
            this.cost = 1.0;
        } else {
            this.cost = cost;
        }
    }

    /**
     * Возвращает город назначения маршрута.
     * @return город назначения
     */
    public City getDestination() {
        return destination;
    }

    /**
     * Возвращает стоимость проезда по маршруту.
     * @return стоимость проезда
     */
    public double getCost() {
        return cost;
    }

    /**
     * Сравнивает маршруты на равенство.
     * @param o объект для сравнения
     * @return true если маршруты равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.cost, cost) == 0 &&
                destination.getName().equals(route.destination.getName());
    }

    /**
     * Возвращает хэш-код маршрута, вычисленный на основе
     * имени города назначения и стоимости проезда.
     * @return значение хэш-кода
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination.getName(), cost);
    }
}