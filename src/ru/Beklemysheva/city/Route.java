/* Продолжение задания 1.10 и 6.5 */

package ru.Beklemysheva.city;

/**
 * Класс представляет маршрут между городами с указанием стоимости пути.
 */
public class Route {
    private final City destination;
    private final double cost;

    /**
     * Создает новый маршрут из города отправления в город назначения с указанной стоимостью.
     * Если переданы некорректные параметры, устанавливает значения по умолчанию.
     *
     * @param destination город назначения
     * @param cost стоимость пути
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
     *
     * @return город назначения
     */
    public City getDestination() {
        return destination;
    }

    /**
     * Возвращает стоимость маршрута.
     *
     * @return стоимость маршрута
     */
    public double getCost() {
        return cost;
    }
}