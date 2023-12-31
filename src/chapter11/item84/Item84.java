package chapter11.item84;

public class Item84 {
    // 프로그램의 동작을 스레드 스케줄러에 기대지 마라.

    // 여러 스레드가 실행중이면 스레드 스케줄러가 어떤 스레드를 얼마나 오래 실행할지 정한다.
    // 정상적인 운영체제 라면 이 작업을 공정하게 수행하지만 구체적인 스케줄링 정책은 운영체재마다 다르다.
    // 이 스케줄러에 기대게 되면 운영체제에 따라 다르게 동작하는 결과를 초래할수 있다.

    // 견고하고 빠른 이식성 좋은 프로갤므을 작성하는 가장 좋은 방법은 실행 가능한 스레드의 평균적인 수를 포렛서 수보다 지나치게 많지 않도록 하는것이다.
    // 실행 가능한 스레드 수를 적게 유지하는 방법은 스레드가 유용한 작업을 완료한 후에 다음 일거리가 생길때까지 대기하게 하는것.
}
