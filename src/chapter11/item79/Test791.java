package chapter11.item79;

import java.util.HashSet;

public class Test791 {
    public static void main(String[] args) {
        ObservableSet<Object> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    // set.addObserver() -> s,e -> sout(e)
    // set.add
    //     @Override
    //    public boolean add(E e) {
    //        boolean added = super.add(e);
    //        if (added) {
    //            notifyElementAdded(e);
    //        }
    //
    //        return added;
    //    }
    // 0~99 까지 잘 출력된다.
}
