/**
 * Задание 1.10. Ограничение количества дорог между городами и управление путями.
 *
 * Измените сущности из задачи 1.3.3 таким образом, чтобы между двумя городами могла существовать только одна прямая дорога.
 * Другие пути между этими городами могут быть проложены только транзитом через другие города.
 * Города можно создавать с заранее заданными путями.
 * В любой момент времени возможно добавление новой дороги в любой город и удаление имеющейся дороги.
 * Задание 6.5. Сравнение городов по набору путей.
 * Измените сущность Город, полученную в задаче 2.1.10.
 * Переопределите метод сравнения объектов по состоянию таким образом, чтобы два города считались одинаковыми тогда,
 * когда у них одинаковый набор путей в другие города.
 * Также обеспечьте, чтобы подвид города из задачи 2.3.3 был сравним с городом из задачи 2.1.10.
 */

package ru.Beklemysheva.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Класс, представляющий город с маршрутами в другие города.
 * Сравнение городов осуществляется по набору городов назначения.
 */
public class City {
    private final String name;
    private final List<Route> routes;

    /**
     * Создает новый город с указанным названием.
     *
     * @param name название города
     */
    public City(String name) {
        this(name, new ArrayList<>());
    }

    /**
     * Создает новый город с указанным названием и списком маршрутов.
     *
     * @param name название города
     * @param routes список маршрутов
     */
    public City(String name, List<Route> routes) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: название города не может быть пустым.");
            this.name = "Город без названия";
        } else {
            this.name = name;
        }
        this.routes = new ArrayList<>(routes);
    }

    /**
     * Добавляет маршрут в город.
     *
     * @param destination город назначения
     * @param cost стоимость маршрута
     */
    public void addRoute(City destination, double cost) {
        if (destination == null) {
            System.out.println("Ошибка: город назначения не может быть null.");
            return;
        }
        if (cost <= 0) {
            System.out.println("Ошибка: стоимость должна быть положительной.");
            return;
        }

        for (Route route : routes) {
            if (route.getDestination().equals(destination)) {
                System.out.println("Ошибка: маршрут до города " +
                        destination.getName() + " уже существует.");
                return;
            }
        }

        routes.add(new Route(destination, cost));
    }

    /**
     * Удаляет маршрут в указанный город.
     *
     * @param destination город назначения
     */
    public void removeRoute(City destination) {
        if (destination == null) {
            System.out.println("Ошибка: город назначения не может быть null.");
            return;
        }

        routes.removeIf(route -> route.getDestination().equals(destination));
    }

    /**
     * Возвращает название города.
     *
     * @return название города
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает список маршрутов из города.
     *
     * @return список маршрутов
     */
    public List<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    /**
     * Сравнивает города по набору городов назначения.
     *
     * @param o объект для сравнения
     * @return true, если города имеют одинаковый набор городов назначения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof City)) {
            return false;
        }

        City otherCity = (City) o;
        return getDestinationSet().equals(otherCity.getDestinationSet());
    }

    /**
     * Генерирует хэш-код на основе множества городов назначения.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDestinationSet());
    }

    /**
     * Возвращает множество городов назначения для сравнения.
     *
     * @return множество городов назначения
     */
    private Set<City> getDestinationSet() {
        Set<City> destinations = new HashSet<>();
        for (Route route : routes) {
            destinations.add(route.getDestination());
        }
        return destinations;
    }

    /**
     * Возвращает строковое представление города и его маршрутов.
     *
     * @return строковое представление города
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        for (Route route : routes) {
            sb.append("    ")
                    .append(route.getDestination().getName())
                    .append(":")
                    .append(route.getCost())
                    .append("\n");
        }
        return sb.toString();
    }
}
