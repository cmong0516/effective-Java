package chapter4.item25;

public class Item25 {
    // 톱레벨 클래스는 한 파일에 하나만 담아라.
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }


}
