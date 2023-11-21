package chapter4.item24;

public class Animal {
    // 1. 정적 멤버 클래스
    // 클래스 내부에 static 으로 선언된 클래스
    // 다른 클래스 안에 선언되고 바깥 클래스의 private 멤버에도 접근 가능.
    // private 으로 선언시 바깥 클래스에서만 접근 가능하다.
    private final String name = "cat";

    public enum Kinds {
        MAMMALS, BIRDS, FISH, REPTILES, INSECT
    }

    private static class PrivateSapmle {
        private int tmep;

        public void method() {
            Animal outerClass = new Animal();
            System.out.println("private" + outerClass.name);
        }
    }

    public static class PublicSample {
        private int temp;

        public void method() {
            Animal outerClass = new Animal();
            System.out.println("public" + outerClass.name);

        }
    }
}
