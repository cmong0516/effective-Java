package chapter11.item84;

public class SlowCountDownLatch {
    private int count;

    public SlowCountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(count + " < 0");
        }
        this.count = count;
    }

    public void await() {
        while (true) {
            synchronized (this) {
                if (count == 0) {
                    return;
                }
            }
        }
    }

    public synchronized void countDown() {
        if (count != 0) {
            count--;
        }
    }

    // 특정 스레드가 다른 스레드들과 비교해 cpu 할당 시간을 충분히 얻지 못한다고 하더라도 Thread.yield 를 써서 해결하려 하지 말자.
    // 어느정도 호전될 수도 있지만 이식성은 좋지 않으며 테스트할 수단이 없다.
}
