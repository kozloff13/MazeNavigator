import java.util.ArrayDeque;

public class Solver {

    char[][] searchRoute;  //Двумерный массив для представления лабиринта

    ArrayDeque<Integer[]> path;  //стек используется для отслеживания местоположения

    int rows;  //Строки
    int cols;  //Столбцы

    public Solver(char[][] city) {

        //инициализирует массив прохождение лабиринта и длины введенного массива
        searchRoute = new char[city.length][city[1].length]; //иметь ввиду эту [1], она может оказаться ненужна

        //копирует содержимое входного массива в массив прохождения лабиринта
        for (int i = 0; i < city.length; i++) {
            for (int j = 0; j < city[i].length; j++) {
                searchRoute[i][j] = city[i][j];
            }
        }
        //установка строк и столбцов
        rows = city.length;
        cols = city[0].length;

        //инициализируем стек, который отслеживает путь в лабиринте в большом размере
        path = new ArrayDeque<>(rows*cols);
    }

    //находим вход в лабиринт
    public void setEntrace() {

        Integer[] temp = new Integer[2]; //доступ в лабиринт. внимание на [2]!!!

        for (int i = 0; i < searchRoute.length; i++) {
            if (searchRoute[i][0] == '@') {

                //складываем в очередь текущее местоположение
                temp[0] = i;
                temp[1] = 0;

                //отмечаем временной отметкой пройденную клетку (?)
                searchRoute[temp[0]][temp[1]] = '@';
                path.addFirst(temp);
            }
        }
    }
    //проверка направления вверх (не помешает)
    public int canSolveUp() {

        Integer[] temp = new Integer[2]; //доступ к очереди
        temp = path.peekFirst();

        int nRow = temp[0] - 1; //переходим в следующую клетку
        int nCol = temp[1];

        //если рядом дорога, вернуть 1
        if (nRow > 0) {
            if (searchRoute[nRow][nCol] == '.') {
                return 1;
            }
        }
        return 0; //иначе 0
    }

    //проверка вправо
    public int canSolveRight() {

        Integer[] temp = new Integer[2]; //получаем доступ
        temp = path.peek();

        int nRow = temp[0];   //переходим дальшн
        int nCol = temp[1] + 1;

        //если рядом дорога, вернуть 1
        if (nCol < cols) {
            if (searchRoute[nRow][nCol] == '.') {
                return 2;
            }
        }
        return 0; //иначе 0
    }

    //проверка вниз
    public int canSolveDown() {

        Integer[] temp = new Integer[2]; //получаем доступ
        temp = path.peek();

        int nRow = temp[0] + 1;  //переход дальше
        int nCol = temp[1];

        //если рядом дорога вернуть 3
        if (nRow < rows) {
            if (searchRoute[nRow][nCol] == '.') {
                return 3;
            }
        }
        return 0; //иначе 0
    }

    //проверка влево
    public int canSolveLeft() {

        Integer[] temp = new Integer[2]; //получаем доступ
        temp = path.peek();

        int nRow = temp[0];   //переходим дальше
        int nCol = temp[1]-1;

        //если рядом дорога вернуть 4
        if (nCol > 0) {
            if (searchRoute[nRow][nCol] == '.') {
                return 4;
            }
        }
        return 0; //иначе 0
    }

    //проверка направлений
    public int[] canSolve() {
        int[] cut = new int[4];
        int place = 0;

        if (canSolveUp() != 0) {
            cut[place] = canSolveUp();
            place++;
        }
        if (canSolveRight() != 0) {
            cut[place] = canSolveRight();
            place++;
        }
        if (canSolveDown() != 0) {
            cut[place] = canSolveDown();
            place++;
        }
        if (canSolveLeft() != 0) {
            cut[place] = canSolveLeft();
        }

        if (place == 0) {
            for (int i = 0; i < 4; i++) {
                cut[i] = 0;
            }
            return cut;
        } else {
            int[] cancut = new int[place];
            for (int i = 0; i < place; i++) {
                cancut[i] = cut[i];
            }
            return cancut;
        }
    }

    public void solveUp() {

        Integer[] current = path.peek(); //текущее положение
        Integer[] temp = new Integer[2]; //получение следующего положения

        int nRow = current[0]-1;  //переход дальше
        int nCol = current[1];

        searchRoute[nRow][nCol] = '-';

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp);  //помещаем в стек, устанавливаем указатель -
    }

    public void solveRight() {
        Integer[] current = path.peek(); //текущее положение
        Integer[] temp = new Integer[2]; //получение следующего положения

        int nRow = current[0];  //переход дальше
        int nCol = current[1] + 1;

        searchRoute[nRow][nCol] = '-';

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp);  //помещаем в стек, устанавливаем указатель -
    }

    public void solveDown() {
        Integer[] current = path.peek(); //текущее положение
        Integer[] temp = new Integer[2]; //получение следующего положения

        int nRow = current[0] + 1;  //переход дальше
        int nCol = current[1];

        searchRoute[nRow][nCol] = '-';

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp);  //помещаем в стек, устанавливаем указатель -
    }

    public void solveLeft() {
        Integer[] current = path.peek(); //текущее положение
        Integer[] temp = new Integer[2]; //получение следующего положения

        int nRow = current[0];  //переход дальше
        int nCol = current[1] - 1;

        searchRoute[nRow][nCol] = '-';

        temp[0] = nRow;
        temp[1] = nCol;

        path.addFirst(temp);  //помещаем в стек, устанавливаем указатель -
    }

    public boolean nearExit() {
        Integer[] temp = new Integer[2];
        temp = path.peek();

        if (temp[1] == searchRoute.length - 1) {
            if (searchRoute[temp[0] + 1][temp[1]] == 'X') {
                return true;
            }
        }
        return false;
    }

    public void gotoExit() {
        Integer[] temp = new Integer[2]; //доступ к стеку
        Integer[] current = path.peek(); //текущее положение

        temp[0] = current[0] + 1;   //Выход снизу
        temp[1] = current[1];

        searchRoute[temp[0]][temp[1]] = '-';

        path.push(temp);
    }

    public void backTrack() {
        path.removeFirst();
    }

    public void solvePath() {

        Integer[] temp;

        while (path.peek() != null) {
            temp = path.pop();

            if (searchRoute[temp[0]][temp[1]] != '@' && searchRoute[temp[0]][temp[1]] != 'X') {
                searchRoute[temp[0]][temp[1]] = '+';
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (searchRoute[i][j] == '-') {
                    searchRoute[i][j] = '.';
                }
            }
        }
    }

    public void solveMap() {

        int[] solve_order; //хранит возможные ходы для каждого положения

        setEntrace(); //поиск начала лабиринта

        while( ! nearExit() ){

            solve_order = canSolve(); /*получение возможных направлений*/

            if(solve_order[0] == 0){  //если тупик
                backTrack();    //идти назад и искать другое направление

            } else { //выбрать направление для решения
                if(solve_order[0] == 1){
                    solveUp();
                }else if(solve_order[0] == 2){
                    solveRight();
                }else if(solve_order[0] == 3){
                    solveDown();
                }else if(solve_order[0] == 4){
                    solveLeft();
                }
            }
        }
        gotoExit(); //после выхода из цикла идем прямо
        //если следующий - выход идем к нему

        solvePath(); //весь путь
        //устанавливаем указатель +

    }

    public void printRoute() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(searchRoute[i][j]);
            }
            System.out.println();
        }
    }


}

