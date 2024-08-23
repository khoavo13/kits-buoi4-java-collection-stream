import collection.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectStream {
    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();
        points.add(new Point(7, 2));
        points.add(new Point(3, 4));
        points.add(new Point(2, 6));
        points.add(new Point(7, 1));

        int totalSum = points.stream()
                .flatMapToInt(point -> Arrays.stream(new int[] {point.getX(), point.getY()}))
                .sum();

        // Tinh tong cac hoanh do
        int sumX = points.stream()
                .mapToInt(Point::getX)
                .sum();

        // Tinh tong cac hoanh do > 5
        int sumX2 = points.stream()
                .filter(p -> p.getX() > 5)
                .mapToInt(Point::getX)
                .sum();

        // Tinh tong cac hoanh do > 5 va x^2+1-y
        int sumX3 = points.stream()
                .filter(p -> p.getX() > 5)
                .mapToInt(p -> {
                    return p.getX()^2 + 1 - p.getY();
                })
                .sum();

        // In ra tong cac hoanh do > 4 va x^2+1-y
        points.stream()
                .filter(p -> p.getX() > 4)
                .mapToInt(p -> {
                    return p.getX()*p.getX() + 1 - p.getY();
                })
                .forEach(res -> System.out.println(res));


        // Tich cua toan bo cac gia tri cua 1/(x^2+y)
        double totalProduct = points.stream()
                .mapToDouble(p -> {
                    int x = p.getX();
                    int y = p.getY();
                    double tmp = x * x + y;
                    return 1.0 / tmp;
                })
                .reduce(1.0, (res, item) -> res*item);

        System.out.println("Tich cua toan bo cac gia tri cua 1/(x^2+y): " + totalProduct);

        // In ra danh sach x + y > 7
        points.stream()
                .filter(p -> p.getX() + p.getY() > 7)
                .forEach(System.out::println);

        points.stream()
                .filter(p -> p.getX() > 5 && p.getY() > 1)
                .forEach(System.out::println);

        int sum5 = points.stream()
                .mapToInt(p -> p.getX() + p.getY())
                .sum();
        System.out.println("Tong 5: " + sum5);
    }
}
