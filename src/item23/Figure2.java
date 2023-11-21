package item23;

abstract class Figure2 {
    abstract double area();
}

class Circle extends Figure2 {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends Figure2 {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}

class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }
}