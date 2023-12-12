package chapter11.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test793 {
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();

                    try {
                        exec.submit(() -> set.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException exception) {
                        throw new AssertionError(exception);
                    }finally {
                        exec.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    // 23 까지 출력한후 교착상태에 빠진다.
    // 백그라운드 스레드가 set.removeObserver 를 호출하면 관찰자를 잠그려 시도하지만 메인스레드가 락을 쥐고있어 얻을수 없다.
    // 그와 동시에 메인 스레드는 백그라운드 스레드가 관찰자를 제거하기만을 기다리는 중이다.

    // set.add -> notifyElementAdded() 실행


}