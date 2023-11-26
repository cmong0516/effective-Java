package chapter6.item37;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Plant37 {
    private enum LifeCycle {ANNUAL,PERENNIAL,BIENNIAL}

    private final String name;
    private final LifeCycle lifeCycle;

    public Plant37(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant37[] garden = {
                new Plant37("바질",    LifeCycle.ANNUAL),
                new Plant37("캐러웨이", LifeCycle.BIENNIAL),
                new Plant37("딜",      LifeCycle.ANNUAL),
                new Plant37("라벤더",   LifeCycle.PERENNIAL),
                new Plant37("파슬리",   LifeCycle.BIENNIAL),
                new Plant37("로즈마리", LifeCycle.PERENNIAL)
        };

        // ordinal() 을 배열 인덱스로 사용.
        Set<Plant37>[] plantsByLifeCycleArr =
                (Set<Plant37>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycleArr.length; i++)
            plantsByLifeCycleArr[i] = new HashSet<>();
        for (Plant37 p : garden)
            plantsByLifeCycleArr[p.lifeCycle.ordinal()].add(p);
        // 결과 출력
        for (int i = 0; i < plantsByLifeCycleArr.length; i++) {
            System.out.printf("%s: %s%n",
                    Plant37.LifeCycle.values()[i], plantsByLifeCycleArr[i]);
        }

        // ANNUAL: [딜, 바질]
        // PERENNIAL: [라벤더, 로즈마리]
        // BIENNIAL: [파슬리, 캐러웨이]

        // 배열은 제네릭과 호환되지 않으니 비검사 형변환을 수행해야 한다.
        // 배열은 각 인덱스의 의미를 모르니 출력 결과에 직접 레이블을 달아야 한다.
        // 정확한 정수값을 사용한다는 것을 직접 보증해야 한다. (타입 불안정)


        // EnumMap 을 사용.
        // 내부 구현에서 배열을 사용하여 타입 안정성과 배열의 장점 모두 가짐.
        Map<LifeCycle, Set<Plant37>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle value : LifeCycle.values()) {
            plantsByLifeCycle.put(value, new HashSet<>());
        }
        for (Plant37 p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }
        System.out.println(plantsByLifeCycle);
        // {ANNUAL=[딜, 바질], PERENNIAL=[라벤더, 로즈마리], BIENNIAL=[파슬리, 캐러웨이]}

        // Stream 사용
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()))
        );
        // {ANNUAL=[딜, 바질], PERENNIAL=[라벤더, 로즈마리], BIENNIAL=[파슬리, 캐러웨이]}

        // Stream 사용과 EnumMap 사용은 다르게 동작한다.
        // EnumMap 은 언제나 식물의 생애 주기당 하나씩 중첩 맵을 만들지만
        // Stream 은 생애주기에 속하는 식물이 있을 때만 만든다.

    }
}
