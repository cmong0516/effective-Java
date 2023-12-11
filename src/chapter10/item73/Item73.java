package chapter10.item73;

public class Item73 {
    // 추상화 수준에 맞는 예외를 던져라.
    // 상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꿔 던져주어야 한다. -> 예외 번역

//    try{
//        ...
//    }catch(LowerLevelException e){
//        throw new HigherLevelException();
//    }

    // 저수준 예외가 디버깅에 도움이 된다면 예외 연쇄 를 사요하는것이 좋다.
    // 예외 연쇄 : 문제의 근본 원인인 저수준 예외를 고수준 예외로 실어 보내는 방식.

}