package chapter7.item46;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Item46 {
    // 스트림에서는 부작용 없는 함수를 사용하라.

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);

//        Map<String, Long> freq = new HashMap<>();

//        try (Stream<String> words = new Scanner(file).tokens()) {
//            words.forEach(word -> {
//                freq.merge(word.toLowerCase(), 1L, Long::sum);
//            });
//        }

        Map<String, Long> freq;
        try (Stream<String> words = new Scanner(file).tokens()) {
            freq = words
                    .collect(groupingBy(String::toLowerCase, counting()));
        }

        // forEach() 는 스트림 계산 결과를 보고할 때만 사용하고 계산할때는 사용하지 말자.

        List<String> topTen = freq.keySet().stream()
                .sorted(Comparator.comparing(freq::get).reversed())
                .limit(10)
                .toList();


        // 1. toMap(keyMapper, valueMapper)
//        Stream.of(values()).collect(toMap(Object::toString, e -> e));

        // 음악가와 그 음악가의 베스트 앨범을 찾기.
//        albums.collect(toMap(Album::artist, a-> a,maxBy(Comparator.comparing(Album::sales))));
        // 앨범들을 돌면서
        // 맵에 아티스트 , 아티스트의 sales 가 가장 높은 앨범 을 추가한다.

        //    public static <T> Collector<T, ?, Optional<T>>
        //    maxBy(Comparator<? super T> comparator) {
        //        return reducing(BinaryOperator.maxBy(comparator));
        //    }

        // maxBy() 는 comparator 를 사용해서 최대값을 찾아준다.

//         words.collect(groupingBy(String::toLowerCase,counting()));
        // words 를 돌면서 그룹화 한다. 키는 String 소문자 값은 갯수.
        // Stream 의 count() 가 있어서 counting() 을 잘 사용하진 않는다.



    }
}
