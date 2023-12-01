package chapter8.item49;

public class Item49 {
    // 매개변수가 유효한지 검사하라.

    // ex) 앞에서 사용했던 requireNonNull(); 으로 null 검사

    // 유효성 검사 비용이 지나치게 높거나 실용적이지 않을경우는 예외.

    // ex )

    private static class Test{
        private String name;

        public Test(String name) {
            if (name != "test") {
                throw new IllegalArgumentException();
            }

            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Test test = new Test("test");

        String name = test.getName();

        System.out.println("name = " + name);

    }
}
