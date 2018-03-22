public class City {

    int rows; //строки
    int cols; //столбцы

    char[][] map; //Карта города

    char ch; //переменная для символа стены и дороги

    public City(int nRows, int nCols) {

        rows = nRows;
        cols = nCols;

        map = new char[rows][cols];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 && j == 0 || i == map.length && j == map.length) {
                    map[i][j] = '.';
                } else {
                    double r = Math.random() * 2;  //Начальное рандомное число, от которого будет строиться лабиринт

                    //Построение лабиринта
                    if (r >= 1) {
                        ch = '#';
                    } else {
                        ch = '.';
                    }
                    if (i == 0) {
                        if (ch == '.' && map[i][j - 1] != '.') {
                            ch = '#';
                        }
                    } else {
                        if (j == 0) {
                            if (ch == '.') {
                                if (map[i - 1][(int) (j + r * 2)] == '.') {
                                    ch = '#';
                                }
                            } else {
                                if (map[i - 1][(int) (j + (r * 2))] == '#') {
                                    ch = '#';
                                }
                            }
                        } else {
                            if (j == map[i].length - 1) {
                                if (ch == '#') {
                                    if (map[i - 1][j - 1] == '#') {
                                        ch = '#';
                                    }
                                } else {
                                    if (map[i - 1][j] == '.' && map[i - 1][j - 1] == '.' &&
                                            map[i][j - 1] == '#') {
                                        ch = '.';
                                    }
                                }
                            } else {
                                if (ch == '#') {
                                    if (map[i - 1][(int) (j + r)] == '#' && map[i][j - 1] == '#' ||
                                            map[i - 1][j] == '#' && map[i][j - 1] == '.' ||
                                            map[i - 1][j - 1] == '#' && map[i - 1][j] == '.')
                                    {
                                        ch = '.';
                                    }
                                } else {
                                    if (map[i - 1][j] == '.' && map[i][j - 1] == '.' &&
                                            map[i - 1][j - 1] == '.' || map[i - 1][j] == '.' &&
                                            map[i - 1][j + 1] == '.') {
                                        ch = '#';
                                    }
                                }
                            }
                        }
                    }
                    map[i][j] = ch;
                    map[0][0] = '@'; //задаем стартовую позицию
                    map[9][9] = 'X'; //задаем финиш
                }
            }
        }
    }

    public void path(City city){
        char[][] searchRoute;
        searchRoute = new char[map.length][map[1].length];

        rows = map.length;
        cols = map[0].length;

        while (map[rows][cols] > 1){
            System.out.println(rows + " " + cols);
            if (rows < rows - 1 && map[rows + 1][cols] == map[rows][cols] - 1) {
                rows++;
                continue;
            }
            if (rows > 0 && map[rows - 1][cols] == map[rows][cols] - 1) {
                rows--;
                continue;
            }
            if (cols < cols - 1 && map[rows][cols+1] == map[rows][cols] - 1) {
                cols++;
                continue;
            }
            if (cols > 0 && map[rows][cols-1] == map[rows][cols] - 1) {
                cols--;
                continue;
            }
        }
        System.out.println(rows + " " + cols);
    }

    public void printPath() {
        return path();
    }

    public void printCity() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}