package chapter10.item71;

public class Item71 {
    // 필요 없는 검사 예외 사용은 피하라.

    // 결과를 코드로 반환하거나 비검사 예외를 던지는 것과 달리 검사 예외는 발생한 문제를 프로그래머가 처리하여 안전성을 높이게끔 해준다.
    // 하지만 과하게 사용하면 불편한 API가 된다.
    // 검사예외를 사용하면 catch 블록을 통해 예외를 처리하거나 더 바깥으로 던져준다.
    // 검사예외는 스트림에서 사용이 불가능하다는 단점이 있다.
//    catch(TheCheckedException e){
//        throw new AssertionError();
//    }
//
//    catch(TheCheckedException e){
//        e.printStackTrace();
//        System.exit(1);
//    }

    // 더 나은 방법이 없다면 비검사 예외를 선택해야 한다.

    // 검사예외를 회피하는 가장 좋은 방법은 적절한 결과 타입을 담은 옵셔널을 반환하는것.
    // 위의 단점은 예외가 발생한 이유를 알려주는 부가 정보를 담을수 없다.


    public static void main(String[] args) {
//        try {
//            obj.action(args);
//        } catch (TheCheckedException e) {
//            ...
//        }

        // 개선
//        if (obj.actionPermitted(args)) {
//            obj.action(args);
//        } else {
//            ...
//        }
//

    }

    // 결론
    // 꼭 필요한 곳에만 검사 예외를 사용하여 프로그램의 안전성을 높이고 API 호출자가 예외 상황에서 복구할 방법이 없다면 비검사 예외를 던지자.

}
