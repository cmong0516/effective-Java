package item23;

public class Figure {
    enum Shape { RECTANGLE, CIRCLE };

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // 다음 필드들은 모양이 사각형(RECTANGLE)일 때만 쓰인다.
    double length;
    double width;

    // 다음 필드는 모양이 원(CIRCLE)일 때만 쓰인다.
    double radius;

    // 원용 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형용 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // 열거타입 선언 , 필드 , switch 등 쓸데없는 코드가 많아 가독성을 해치고 메모리 누수.
    // 필드를 final 로 선언하려면 사용하지 않는 필드도 초기화 해줘야한다.

    // -> 계층구조로 바꿔보자.
    // 1. 계층 구조의 루트가 될 추상 클래스를 정의한다.
    // 2. 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 클래스로 선언한다. EX) area()
    // 3. 태그 값에 상관없는 메서드 들을 루트 클래스에 일반 메서드로 추가한다.
    // 4. 모든 하위 클래스에서 공통으로 사용하는 데이터 필드를 루트 클래스로 올린다.

    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}