package chapter11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread2 {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean getStopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!getStopRequested()) {
                i++;
            }
        });


        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }


    // backgroundThread 가 실행되어 !getStopRequested 의 값이 true 일 때까지 i++ 을 반복한다.
    // 동기화 처리 해주기 전에는 stopRequested 의 값을 확인하지 않아 무한히 진행되었지만 쓰기(requestStop) , 읽기(getStopRequested)모두 동기화를 적용시켰다.
    // 이렇게 쓰기와 읽기 양쪽에 모두 동기화 처리를 해주어야 한다.

    // 하지만 이런 가변데이터를 사용하는것은 단일 스레드 에서만 쓰도록 하자.
    // 근본적으로 불변데이터를 사용하는게 안전하기 때문이다.
}
