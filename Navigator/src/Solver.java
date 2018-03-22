public class Solver implements Navigator{

    int rows;
    int cols;

    char[][] path;

    @Override
    public void searchRoute(char[][] map) {
        //инициализирует массив прохождение лабиринта и длины введенного массива
        path = new char[map.length][map[1].length];

        //копирует содержимое входного массива в массив прохождения лабиринта
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                path[i][j] = map[i][j];
            }
        }

        //установка строк и столбцов
        rows = map.length;
        cols = map[0].length;

        //инициализируем стек, который отслеживает путь в лабиринте в большом размере
        //path = new ArrayDeque<>(rows*cols);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                while (map[i][j] > 1){
                    System.out.println(i + " " + j);
                    if (i < rows - 1 && map[i + 1][j] == map[i][j] - 1) {

                        i++;
                        continue;
                    }
                    if (i > 0 && map[i - 1][j] == map[i][j] - 1) {
                        i--;
                        continue;
                    }
                    if (j < cols - 1 && map[i][j+1] == map[i][j] - 1) {
                        j++;
                        continue;
                    }
                    if (j > 0 && map[i][j-1] == map[i][j] - 1) {
                        j--;
                        continue;

                    }
                }

                System.out.println(i + " " + j);
            }
        }
    }


    public void printPath() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(path[i][j]);
            }
            System.out.println();
        }
    }
}
