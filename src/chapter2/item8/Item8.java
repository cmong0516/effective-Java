package chapter2.item8;

public class Item8 {
    public static void main(String[] args) {
        // try with resources 를 사용하여 자동으로 리소스를 닫아준다.
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕");
        }
    }
}
