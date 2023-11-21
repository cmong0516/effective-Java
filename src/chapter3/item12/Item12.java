package chapter3.item12;

import chapter3.item11.PhoneNumber;

public class Item12 {
    //toString 을 항상 재정의 하라.
    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(011, 213, 4244);
        System.out.println(phoneNumber.toString());
        // 재정의 전 phoneNumber 의 toString()
        // chapter3.item11.PhoneNumber@4c28

        // PhoneNumber 는 지역코드 , prefix, lineNum 세가지 핵심 정보를 담고있다.

        // 자동생성 toString
        // PhoneNumber{areaCode=9, prefix=213, lineNum=4244}

        // 상위 클래스에서 올바르게 재정의된 toString 이 없다면 toString 재정의로 디버깅 효율성을 높일수 있다.
        // toString 은 해당 객체에 관한 명확하고 유용한 정보를 읽기 좋은 형태로 반환해야 한다.
    }
}
