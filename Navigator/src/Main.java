public class Main{

    public static void main(String[] args) {

        City city = new City(10,10);
        city.printCity();

        System.out.println();

        Solver sol = new Solver(city.map);
        sol.solveMap();
        sol.printRoute();


    }

}
