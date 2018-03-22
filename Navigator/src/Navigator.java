public interface Navigator {
    /**
     * Поиск кратчайшего маршрута на карте города между двумя точками
     * @param map карта города
     * @return карта города с построенным маршрутом
     */
    void searchRoute(char[][] map);
    void printPath();

}
