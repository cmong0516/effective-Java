package chapter11.item79;

import chapter4.item18.ForwardingSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> s) {
        super(s);
    }


    private final List<SetObserver<E>> observers
            = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }
    }

    // 외계인 메서드 : 응답 불가와 안전 실패를 유발할 수 있는 메서드로 동기화된 영역에서 재정의 할수 있는 메서드 혹은 클라이언트가 넘겨준 함수 객체 등을 동기화된 클래스 관점에서 외계인 메서드 라고 한다.
    // 외계인 메서드를 동기화 블록 바깥으로 옮겼다.
    private void notifyElementAdded2(E element) {
        List<SetObserver<E>> snapshot = null;

        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }
        for (SetObserver<E> eSetObserver : snapshot) {
            eSetObserver.added(this, element);
        }
    }



    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
            //
        }

        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            result |= add(e);
        }

        return result;
    }

    public static void main(String[]args) {

        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

//        set.addObserver((s, e) -> System.out.println(e));

        set.addObserver(new SetObserver<>() {
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
