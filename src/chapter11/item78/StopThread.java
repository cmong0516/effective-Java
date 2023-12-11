package chapter11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });

        backgroundThread.start();

        // 1초간 중지.
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;

        // backgroundThread 는 stopRequested 가 true 가 될때까지 i++ 을 실행한다.
        // backgroundThread 를 실행시키고 1초 뒤에 stopRequested 를 true 로 변경하였기 때문에
        // 1초 + 실행시간 뒤에 멈출거같지만 영원히 실행되고있다.

        // 원인은 동기화 하지 않으면 메인 스레드가 수정한 값을 백그라운드 스레드가 언제쯤 보는지 보증할수가 없다.
    }
}
