package item21;

import java.util.ArrayList;
import java.util.List;

public class Item21 implements Sized{
    // 인터페이스는 구현하는 쪽을 생각해 설계하라.

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        list.removeIf(n -> n == 2);

        System.out.println("list = " + list);

        //    default boolean removeIf(Predicate<? super E> filter) {
        //        Objects.requireNonNull(filter);
        //        boolean removed = false;
        //        final Iterator<E> each = iterator();
        //        while (each.hasNext()) {
        //            if (filter.test(each.next())) {
        //                each.remove();
        //                removed = true;
        //            }
        //        }
        //        return removed;
        //    }

        // 기존 인터페이스에 디폴트 메서드로 새 메서드를 추가하는 일은 꼭 필요한 경우가 아니면 피해야한다.
        // 추가하려는 디폴트 메서드가 기존 구현체들과 출돌하는지 많은 확인이 필요하다.

        Item21 item21 = new Item21();

        System.out.println(item21.isEmpty());

        // 디폴트 메서드으 규칙
        //1) 클래스가 항상 이긴다. 클래스나 슈퍼 클래스에서 정의한 메서드가 디폴트 메서드보다 항상 우선권을 갖는다.
        //2) 1번 규칙 이외의 상황에서는 서브 인터페이스가 이긴다. 상속관계를 갖는 인터페이스에서 같은 시그니처를 갖는 메서드를 정의할때는 서브 인터페이스가 이긴다. (B가 A를 상속 받는다면 B가 A를 이긴다.)
        //3) 여전히 디폴트 메서드의 우선순위가 결정되지 않았다면 여러 인터페이스를 상속받는 클래스가 명시적으로 디폴트 메서드를 오버라이드하고 호출해야한다.


        // public class D implements A {}     -> 클래스가 이긴다. D > A
        //
        //public class C extends D implements B, A {     -> 우선순위는 D , D 는 A 의 구현체. -> A  -> D 는 A 의 디폴드 메서드를 구현.
        //  public static void main(String... args) {
        //    new C().hello();             -> A 구현체 D 외의 C 의 인터페이스 B 의 hello 가 실행된다.
        //  }
        //}

        // 위의 정의로 해결되지 않을경우 ??
        // A.super.hello();
        // B.super.hello(); 등으로 명시해준다.
        // 애초에 명시하면 좋은거 아닌가




    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return Sized.super.isEmpty();
    }
}
