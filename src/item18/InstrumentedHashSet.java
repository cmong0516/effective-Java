package item18;

import java.util.*;

public class InstrumentedHashSet<E> extends HashSet<E> {
    // 추가된 원소의 수
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    //    public boolean addAll(Collection<? extends E> c) {
    //        return s.addAll(c);
    //    }

    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        // 여기서 3번

        return super.addAll(c);
        // 여기서 또 3번
    }

    //    public boolean addAll(Collection<? extends E> c) {
    //        boolean modified = false;
    //        for (E e : c)
    //            if (add(e))
    //                modified = true;
    //        return modified;
    //    }
    // Collection 격체 수만큼 add 메서드를 실행.

    // Set 과 HashSet 같은 메서드를 실행했는데 결과가 다르게 나온다.
    // 이유는 Set , HashSet

    public int getAddCount() {
        return addCount;
    }

}