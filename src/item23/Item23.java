package item23;

public class Item23 {
    // 태그달린 클래스 보다는 클래스 계층구조를 활용하라.

    public static void main(String[] args) {
        Circle circle = new Circle(4);
        System.out.println("circle.area() = " + circle.area());

        Rectangle rectangle = new Rectangle(10, 4);
        System.out.println("rectangle.area() = " + rectangle.area());

        Square square = new Square(10);
        System.out.println("square.area() = " + square.area());
    }
}
