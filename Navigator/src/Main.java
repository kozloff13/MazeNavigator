public class Main{

    public static void main(String[] args) {

        City city = new City(10,10);
        city.printCity();

        Navigator navi = new Solver();
        navi.printPath();

    }

}
