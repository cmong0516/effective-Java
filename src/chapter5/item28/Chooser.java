package chapter5.item28;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser {
    private final Object[] choiceArray;

    public Chooser(Object[] choiceArray) {
        this.choiceArray = choiceArray;
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }

    // 이 클래스를 사용하려면 choose() 가 호출될 때마다 반환된 Object 를 원하는 타입으로 형변환 해야한다.
    // 혹시나 타입이 다른 원소가 들어있다면 런타임 형변환 오류가 발생.
}
