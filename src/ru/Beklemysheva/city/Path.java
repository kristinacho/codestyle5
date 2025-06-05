/**
 * Задание 6.5. Разработка сущности Маршрут на основе сущности Город из задачи 1.4.8.
 *
 * Сущность Маршрут представляет собой путь между двумя городами и обладает следующими характеристиками:
 * - Имеет город начала и город конца пути.
 * - Инициализация маршрута возможна только при указании обеих точек начала и конца (null недопустим).
 * - Точки начала и конца могут быть изменены в любой момент, но всегда должны существовать.
 * - Может вернуть массив городов, представляющий маршрут от начала к концу, включая обе точки.
 *   Если путь построить невозможно — возвращается пустой массив.
 * - Маршрут может быть приведён к строке, содержащей названия всех городов маршрута в порядке следования.
 * - Создание объекта и изменение точек начала и конца выполняется за константное время O(1).
 */

package ru.Beklemysheva.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, представляющий маршрут между двумя городами.
 * Позволяет находить путь между городами и представлять его в виде строки.
 */
public class Path {
    private City start;
    private City end;

    /**
     * Создает новый маршрут между двумя городами.
     *
     * @param start город начала (не null)
     * @param end город конца (не null)
     */
    public Path(City start, City end) {
        if (start == null || end == null) {
            System.out.println("Ошибка: города начала и конца не могут быть null.");
            return;
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Устанавливает новый город начала маршрута.
     *
     * @param start новый город начала (не null)
     */
    public void setStart(City start) {
        if (start == null) {
            System.out.println("Ошибка: город начала не может быть null.");
            return;
        }
        this.start = start;
    }

    /**
     * Устанавливает новый город конца маршрута.
     *
     * @param end новый город конца (не null)
     */
    public void setEnd(City end) {
        if (end == null) {
            System.out.println("Ошибка: город конца не может быть null.");
            return;
        }
        this.end = end;
    }

    /**
     * Возвращает массив городов, представляющих маршрут.
     * Если путь не найден, возвращает пустой массив.
     *
     * @return массив городов в порядке прохождения
     */
    public City[] getRoute() {
        if (start.equals(end)) {
            return new City[]{start};
        }

        List<City> path = findPath(start, end, new ArrayList<>());
        return path.toArray(new City[0]);
    }

    /**
     * Рекурсивный поиск пути (DFS).
     *
     * @param current текущий город
     * @param target город назначения
     * @param visited список посещенных городов
     * @return список городов пути или пустой список, если путь не найден
     */
    private List<City> findPath(City current, City target, List<City> visited) {
        visited.add(current);

        if (current.equals(target)) {
            return new ArrayList<>(Collections.singletonList(current));
        }

        for (Route route : current.getRoutes()) {
            City neighbor = route.getDestination();
            if (!visited.contains(neighbor)) {
                List<City> path = findPath(neighbor, target, new ArrayList<>(visited));
                if (!path.isEmpty()) {
                    path.add(0, current);
                    return path;
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Возвращает строковое представление маршрута.
     *
     * @return строка вида "A -> B -> C" или "Путь не найден"
     */
    @Override
    public String toString() {
        City[] route = getRoute();
        if (route.length == 0) {
            return "Путь не найден";
        }

        StringBuilder sb = new StringBuilder();
        for (City city : route) {
            sb.append(city.getName()).append(" -> ");
        }
        sb.setLength(sb.length() - 4); // Удаляем последнюю стрелку
        return sb.toString();
    }
}
