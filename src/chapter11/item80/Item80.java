package chapter11.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item80 {
    // 스레드보다는 실행자 , 태스크 , 스트림을 애용하라.

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();

        // exec.execute(runnable);
        // 이렇게 하면 이 실행자에 실행할 태스크를 넘길수 있다.

        exec.shutdown();
        // 이렇게 종료.

        // 실행자의 주요 기능
        // 1. 특정 태스크가 완료되기를 기다린다.
        // 2. 태스크 모음 중 하무것 하나 혹은 모든 태스크가 완료되기를 기다린다.
        // 3. 실행자 서비스가 종료하기를 기다린다.
        // 4. 완ㄹ된 태스크들의 결과를 차례로 받는다.
        // 5. 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다.

        // 필요한 실행자 대부분은 Executors 의 정적 팩터리들을 이요해 생성할수 있다.
        // 평범하지 않은 실행자를 원한다면 ThreadPoolExecutor 클래스를 직접 사용해도 된다.
        // 실행자 서비스를 사용하기 까다로운 작은 프로그램이나 서버라면 Executors.newCachedThreadPool 이 일반적으로 좋다.
        // 무거운 프로그램 이라면 Executors.newFixedThreadPool or ThreadPoolExecutor 를 직접 사용한다.

        // 작업 큐를 손수 만드는 일은 삼가하고 스레드를 직접 다루는 것도 일반적으로 하지 않는게 좋다.

        // 작업 단위를 나타내는 태스크 에는 두가지가 있다.
        // 1. Runnable
        // 2. Callable

        // 태스크를 수행하는 일반적인 매커니즘이 바로 실행자 서비스이다.
    }
}
