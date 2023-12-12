package chapter11.item81;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class Item81 {
    // wait , notify 보다는 동시성 유틸리티를 애용하라.
    // wait , notify 를 올바르게 사용하기가 아주 까다로웠으나 java5 에서 등장한 동시성 유틸리티가 하드코딩 해야 했던 작업들을 해결해준다.


    // java.util.concurrent 의 고수준 유틸리티는 세가지 범주로 나뉜다.
    // 1. 실행자 프레임워크
    // 2. 동시성 컬렉션
    // 3. 동기화 장치


    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static String intern1(String s) {
        String previousValue = map.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }


    // ConcurrentHashMap 은 get 같은 검색 기능에 최적화 되어 있다.
    // 따라서 get 을 먼저 호출한 뒤에 putIfAbsent 를 호출하면 더 빠르게 실행할수 있다.
    public static String intern2(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null) {
                result = s;
            }
        }

        return result;
    }


    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();
                try {
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    done.countDown();
                }
            });
        }

        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;
    }

    // ready 래치는 작업자 스레드 들이 준비가 완료되었음을 타이머 스레드에 통지할때 사용된다.
    // 통지를 끝낸 작업자 스레드들은 start가 열리길 기다린다.
    // 작업자 스레드가 ready.countDown 을 호출하면 타이머 스레드가 시작 시각을 기록하고 start.countDown 을 호출하여 기다리던 작업자 스레드들을 깨운다.
    // 타이머 스레드는 세번째 래치인 done 이 열리길 기다린다.
    // done 래치는 마지막 남은 작업자 스레드가 동작을 마치고 countDown 을 호출하면 열린다.
    // 타이머 스레드는 done 래치가 열리자 마자 깨어나 종료 시각을 기록한다.

    // time 메서드에 넘겨진 실행자 executor 는 concurrency 만큼 스레드를 생성할수 있어야 한다.
    // 그렇지 않으면 스레드 기아 교착상태에 빠진다.
    // InterruptedException 을 캐치한 작업자 스레드는 Thread.currentThread().interrupt()를 사용해 자신은 run 메서드에서 빠져나온다.
    // 시간 간격을 잴때는 System.currentTimeMillis 가 아닌 System.nanoTime 을 사용!
}
