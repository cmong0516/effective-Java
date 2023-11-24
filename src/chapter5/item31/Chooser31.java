package chapter5.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Chooser31<T> {
    private final List<T> choiseList;
    private final Random rnd = new Random();

//    public Chooser31(List<T> choiseList) {
//        this.choiseList = choiseList;
//    }
    // choices 컬렉션은 T 타입 값을 생산.
    // 생산 : <? extends T>

    public Chooser31(Collection<? extends T> choices) {
        this.choiseList = new ArrayList<>(choices);
    }

    public T choose() {
        return choiseList.get(rnd.nextInt(choiseList.size()));
    }
}
