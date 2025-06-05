/* Продолжение задания 1.10 и 6.5 */

package ru.Beklemysheva.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Класс, представляющий город с маршрутами в другие города.
 */
public class City {
    private final String name;
    private final List<Route> routes;

    /**
     * Создает новый город с указанным названием.
     * @param name название города
     */
    public City(String name) {
        this(name, new ArrayList<>());
    }

    /**
     * Создает новый город с указанным названием и маршрутами.
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
     * Добавляет новый маршрут из этого города.
     * @param destination город назначения
     * @param cost стоимость проезда
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
        if (hasRouteTo(destination)) {
            System.out.println("Ошибка: маршрут уже существует.");
            return;
        }

        routes.add(new Route(destination, cost));
    }

    /**
     * Проверяет наличие маршрута до указанного города.
     * @param destination город назначения
     * @return true если маршрут существует
     */
    private boolean hasRouteTo(City destination) {
        return routes.stream()
                .anyMatch(route -> route.getDestination().getName().equals(destination.getName()));
    }

    /**
     * Удаляет маршрут до указанного города.
     * @param destination город назначения
     */
    public void removeRoute(City destination) {
        if (destination == null) {
            System.out.println("Ошибка: город назначения не может быть null.");
            return;
        }
        routes.removeIf(route -> route.getDestination().getName().equals(destination.getName()));
    }

    /**
     * Возвращает название города.
     * @return название города (не null)
     */

    public String getName() {
        return name;
    }

    /**
     * Возвращает список маршрутов из этого города.
     * @return новый список маршрутов (не null)
     */

    public List<Route> getRoutes() {
        return new ArrayList<>(routes);
    }

    /**
     * Сравнивает города по названию и маршрутам.
     * @param o объект для сравнения
     * @return true если города равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name) && routesEqual(city.routes);
    }

    /**
     * Сравнивает маршруты двух городов.
     * @param otherRoutes маршруты другого города
     * @return true если маршруты эквивалентны
     */
    private boolean routesEqual(List<Route> otherRoutes) {
        if (routes.size() != otherRoutes.size()) return false;

        Set<String> thisDestinations = new HashSet<>();
        Set<String> otherDestinations = new HashSet<>();

        routes.forEach(r -> thisDestinations.add(r.getDestination().getName()));
        otherRoutes.forEach(r -> otherDestinations.add(r.getDestination().getName()));

        return thisDestinations.equals(otherDestinations);
    }

    /**
     * Возвращает хэш-код города, вычисленный на основе его имени.
     * @return значение хэш-кода
     */

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Возвращает строковое представление города
     * @return строковое представление города с маршрутами
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