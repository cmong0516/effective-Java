package item24;

public class TestClass {
    private String name = "mong";

    public class PublicSample {
        public void printName() {
            System.out.println(name);
        }

        public void callTestClassMethod() {
            TestClass.this.testMethod();
        }
    }

    public PublicSample createPublicSample() {
        return new PublicSample();
    }

    public void testMethod() {
        System.out.println("Hello World!");
    }

}
