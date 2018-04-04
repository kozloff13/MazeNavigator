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
                    map[1][1] = '@'; //задаем стартовую позицию
                    map[8][8] = 'X'; //задаем финиш
                }
            }
        }
    }

    public void printCity() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getMap() {
        return map;
    }
}