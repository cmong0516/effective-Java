package chapter7.item45;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Anagrams {
    // 아나그램 이란 : 철자를 구성하는 아랖벳이 같고 순서만 다른 단어.
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
                // key 아나그램 , value new TreeSet<>() 에 단어를 넣은값.
            }
        }

        //     default V computeIfAbsent(K key,
        //            Function<? super K, ? extends V> mappingFunction) {
        //        Objects.requireNonNull(mappingFunction);
        //        V v;
        //        if ((v = get(key)) == null) {
        //            V newValue;
        //            if ((newValue = mappingFunction.apply(key)) != null) {
        //                put(key, newValue);
        //                return newValue;
        //            }
        //        }
        //
        //        return v;
        //    }
        // computeIfAbsent() 는 주어진 키에 대한 값이 존재하지 않을때 새 값을 계산하고 해당키와 계산된 값을 맵에 추가한다.

        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize) {
                System.out.println(group.size() + ": " + group);
            }
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    // String 을 char 로 나누어 정렬한후 String 으로 반환.
    // 아나그램인지 확인할수 있게 해준다.
}

// Map 에서 groupBy 형식으로 갯수를 알고 싶을때 : computeIfAbsent() 를 사용하라.