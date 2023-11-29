package chapter7.item45;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamAnagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {

                    // Stream 이 너무 과하게 사용되었다. 이부분을 메서드로 줄여보자.
//            words.collect(
//                    groupingBy(words -> words.chars().sorted()
//                            .collect(StringBuilder::new,(sb,c) -> sb.append((char)c),
//                                    StringBuilder::append).toString()))


            // 람다에서는 타입 이름을 자주 생략하므로 네이밍에 더 신경써서 의도를 표현하자.
            words.collect(groupingBy(StreamAnagrams::alphabetize))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(group -> System.out.println(group.size() + ": " + group));

            // alphabetize() 를 사용하여 훨씬 보기좋은 코드가 되었다.
            // try with resource 로 파일 연결도 자동으로 close()
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
