package item13;

public class Item13 {
    // clone 재정의는 주의해서 진행하라.

    // x.clone() != x = true
    // x.clone().getClass() == x.getClass()
    // x.clone().equals(x)
    // x.clone().getClass() == x.getClass()
    public static void main(String[] args) {
        Person person = new Person("사람1", 30);

        try {
            Person clone = person.clone();
            System.out.println(person.toString());
            System.out.println(clone.toString());
            System.out.println("person.equals(clone) = " + person.equals(clone));

            clone.setName("사람2");

            System.out.println(person.toString());
            System.out.println(clone.toString());

            person.setName("사람3");

            System.out.println(person.toString());
            System.out.println(clone.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Stack13 stack = new Stack13();

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push(1);

        Stack13 clone = stack.clone();

        System.out.println(clone.pop());

        // 변환생성자 , 변환 팩터리를 사용하고 배열만 clone 메서드 방식을 사용하라.

    }
}
