import collection.Point;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainCollection {
    public static void main(String[] args) {
//        List<String> colors = new ArrayList<>();
//        colors.add("Red");
//        colors.add("Yellow");
//        colors.add("Orange");
//
//        // Truy cap vao 1 phan tu chi dinh
//        System.out.println(colors.get(1));
//
//        // Thay doi phan tu chi dinh
//        colors.set(1, "Blue");
//
//        // Xoa phan tu theo gia tri
//        colors.remove("Red");
//        System.out.println(colors);
//
//        // Xoa phan tu theo chi so
//        colors.remove(1);
//        System.out.println(colors);
//
//        for (String c : colors){
//            System.out.println(c);
//        }
//
//        // Tao 1 mảng
//        String[] colorsArray = {"Red", "Blue", "Yellow"};
//        // Tạo Array List từ Array
//        ArrayList<String> colorsList = new ArrayList<>(Arrays.asList(colorsArray));
//
//        // Sap xep thuong
//        Collections.sort(colorsList);
//        System.out.println(colorsList);
//
//        // Su dung bieu thuc lambda de sap xep danh sach giam dan
//        Collections.sort(colorsList, (s1, s2) -> s2.compareTo(s1));
//        System.out.println(colorsList);


        // ================================
        // So sanh 1 doi tuong co cau truc
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 5));
        points.add(new Point(2, 6));
        points.add(new Point(5, 2));
        points.add(new Point(6, 0));

        Comparator<Point> x_y_compare = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int res = Integer.compare(o1.getX(), o2.getX());
                if (res == 0) {
                    return Integer.compare(o1.getY(), o2.getY());
                }
                return res;
            }
        };

        Collections.sort(points, x_y_compare);
        for (Point p : points) {
            System.out.println(p.toString());
        }

        /*
            lambda expression
         */
        // Predicate
        Predicate<Point> sumPoint1 = p -> p.getX() + p.getY() > 7;
        Predicate<Point> sumPoint2 = p -> {
            if (p.getX() > 5) {
                return p.getX() + p.getY() > 7;
            }
            return p.getX() + p.getY() > 6;
        };
        System.out.println("====================");
        for (Point p : points) {
            if (sumPoint2.test(p)) {
                System.out.println(p.toString());
            }
        }
        // Function
        Function<Point, Integer> sumPoint3 = p -> p.getX() + p.getY();
        Function<Point, Integer> sumPoint4 = p -> {
            return p.getX() > p.getY() ? p.getX() - p.getY() : p.getY() - p.getX();
        };
        System.out.println("====================");
        for (Point p : points) {
            System.out.println(sumPoint4.apply(p));
        }
        // Consumer
        Consumer<Point> printMessage = p -> {
            if (p.getX() > p.getY()) {
                System.out.println(p.getX() - p.getY());
            } else {
                System.out.println(p.getY() - p.getX());
            }
        };
        System.out.println("====================");
        points.forEach(printMessage);
        System.out.println("====================");
        for (Point p : points) {
            printMessage.accept(p);
        }
        // stream()
        System.out.println("====================");
        points.stream()
                .filter(p -> p.getX() > 3)
                .forEach(System.out::println);

        // =================================
        System.out.println("====================");
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Annna");

        // Loc cac ten bat dau bang A
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        List<String> filterNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(filterNames);

        // Sap xep theo thu tu giam dan
        names.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Tinh tong
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Tong: " + sum);

        // Tinh trung binh
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("Trung binh: " + average);

        // Tim gia tri lon nhat
        OptionalInt max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max();
        System.out.println("Max: " + (max.isPresent() ? max.getAsInt() : "Not present"));

        // Su dung reduce tinh tong
        int sum1 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Tong: " + sum1);

        // Su dung reduce tinh tich
        int product = numbers.stream()
                .reduce(1, (kq, item) -> kq * item);
        System.out.println("Tich: " + product);

        List<List<Integer>> listOfList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> combinedList = listOfList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(combinedList);

        // Tinh tong cua 1 + 1/2 + 1/3 + 1/4 + 1/5
        double sum3 = numbers.stream()
                .mapToDouble(Integer::doubleValue)
                .reduce(0d, (kq, item) -> kq + 1/item);
        System.out.println("Tong " + sum3);
    }
}