package chapter3.item10;

import java.util.Objects;

public class Item10 {
    // equals 는 함부로 재정의 하지 말고 재정의 할경우 대칭, 추이성 , 일관성 을 고려한다.
    // Objects.equals 를 활용하자.
    public static void main(String[] args) {
        Point point1 = new Point(1, 2);
        ColorPoint colorPoint1 = new ColorPoint(1, 2, Color.GREEN);

        System.out.println(point1.equals(colorPoint1));
        System.out.println(colorPoint1.equals(point1));

        System.out.println(Objects.equals(point1,colorPoint1));
    }
}
