package chapter5.item29;

public class Item29 {
    // 이왕이면 제네릭 타입으로 만들어라.


    // Stack 생성자에서 Object[] -> E[] 로 형변환 해주는 방법 1.
    // push , pop 메서드에서 E 타입으로 형변환, pop return @SuppressWarnings -> 방법 2.



    public static void main(String[] args) {
        Stack29<String> stack29 = new Stack29<>();
        Stack29<Integer> stack291 = new Stack29<>();

        stack29.push("hello");
        stack29.push("world");

        stack291.push(1);
        stack291.push(2);

        System.out.println("stack29.pop() = " + stack29.pop());
        // world

        System.out.println("stack291 = " + stack291.pop());
        // 2
    }
}
