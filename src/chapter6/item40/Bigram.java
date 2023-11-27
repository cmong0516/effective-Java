package chapter6.item40;

import java.util.HashSet;
import java.util.Set;

public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Bigram bigram) {
        return bigram.first == first && bigram.second == second;
    }

    public int hashCode() {
        return 31 * first +second;
    }

    public static void main(String[] args) {
        Set<Bigram> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a';  ch<= 'z' ; ch++) {
                set.add(new Bigram(ch, ch));
            }
        }
        System.out.println(set.size());

        // Set 은 중복을 허용하지 않으니 26을 출력해야 정상일것 같지만 260이 출력된다.
        // equals , hashCode 두 메서드를 재정의 하지 않고 다중정의 해버렸다.
        // @Override 어노테이션을 사용하면 컴파일 단계에서 에러를 잡아준다.
    }
}
