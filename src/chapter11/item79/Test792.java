package chapter11.item79;

import java.util.HashSet;

public class Test792 {
    public static void main(String[] args) {
        ObservableSet<Integer> set =
                new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) // 값이 23이면 자신을 구독해지한다.
                {
                    s.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    // 23 일때 본인 값을 지우게 했더니 23까지 출력되고 ConcurrentModificationException 이 발생했다.
    // 관찰자의 added 메서드 호출이 일어난 시점이 notifyElementAdded 가 관찰자들의 리스트를 순회하는 도중이기 때문.

    // added -> ObservableSet.removeObserver() -> observers.remove()
    // 리스트에서 원소를 삭제하려고 하는데 마침 리스트를 순회하는 도중이라 허용되지 않는 동작이다.
    // notifyElementAdded 메서드에서 수행하는 순회는 동기화 블록 안에 있으므로 동시 수정이 일어나지 않도록 보장하지만
    // 정작 자신이 콜백을 거쳐 되돌아와 수정하는 것까지 막지는 못한다.
}
