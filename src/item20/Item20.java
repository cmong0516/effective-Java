package item20;

import java.util.Collections;
import java.util.List;

public class Item20 {
    // 추상 클래스 보다는 인터페이스를 우선하라.
    // 가수 , 작곡가 를 예로들면 가수와 작곡가 모두를 겸하는 사람은 어떻게 구분할 것인가 ??

    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        List<Integer> list = IntArrays.intArraysAsList(a);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
