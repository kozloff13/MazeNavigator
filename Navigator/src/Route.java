import javafx.util.Pair;

import java.util.ArrayList;

public class Route {

    static char[][] searchRoute;

    int rows;
    int cols;

    static boolean is_exit;

    private final Navigator sol;
    private final City city;


    public Route(final City city, final Navigator sol) {
        this.sol = sol;
        this.city = city;

        searchRoute = new char[city.getMap().length][city.getMap()[1].length];

        //копирует содержимое входного массива в массив прохождения лабиринта
        for(int i = 0; i < city.getMap().length; i++){
            for(int j = 0; j < city.getMap()[i].length; j++){
                searchRoute[i][j] = city.getMap()[i][j];
            }
        }

        //установка строк и столбцов
        rows = city.getMap().length;
        cols = city.getMap()[0].length;
    }



    public void findPath(char[][] map, int x,int y) {
        ArrayList<Pair<Integer,Integer>> queue = new ArrayList<>(rows*cols);
        queue.add(new Pair<>(x,y));
        map[x][y] = '@';
        while (queue.size() > 0)  {

            Pair<Integer,Integer> cur = queue.remove(queue.size() - 1);

            if (x < rows) {
                if (map[x + 1][y] == '.') {
                    queue.add(new Pair<>(x + 1, y));
                    map[x + 1][y] = '+';
                    x++;
                    continue;
                }
                if (map[x + 1][y] == 'X') {
                    queue.add(new Pair<>(x + 1, y));
                    is_exit = true;
                }
            }

            if (x > 0) {
                if (map[x - 1][y] == '.') {
                    queue.add(new Pair<>(x - 1, y));
                    map[x - 1][y] = '+';
                    x--;
                    continue;
                }
                if (map[x - 1][y] == 'X') {
                    queue.add(new Pair<>(x - 1, y));
                    is_exit = true;
                }
            }

            if (y < cols - 1) {
                if (map[x][y + 1] == '.') {
                    queue.add(new Pair<>(x, y + 1));
                    map[x][y + 1] = '+';
                    y++;
                    continue;
                }
                if (map[x][y + 1] == 'X') {
                    queue.add(new Pair<>(x, y + 1));
                    is_exit = true;
                }
            }

            if (y > 0) {
                if (map[x][y - 1] == '.') {
                    queue.add(new Pair<>(x, y - 1));
                    map[x][y - 1] = '+';
                    y--;
                    continue;
                }
                if (map[x][y - 1] == 'X') {
                    queue.add(new Pair<>(x, y - 1));
                    is_exit = true;
                }
            }
        }
    }

    public void printRoute() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(searchRoute[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        City city = new City(10,10);
        city.printCity();

        final Navigator sol = new Solver();
        final Route route = new Route(city, sol);

        System.out.println();

        if (is_exit = true) {
            route.findPath(searchRoute, 1, 1);
            route.printRoute();
        } else {
            System.out.println("null");
        }
    }
}
