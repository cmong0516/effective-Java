package item12;

import item11.PhoneNumber;

public class Item12 {
    //toString 을 항상 재정의 하라.
    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(011, 213, 4244);
        System.out.println(phoneNumber.toString());
        // 재정의 전 phoneNumber 의 toString()
        // item11.PhoneNumber@4c28

        // PhoneNumber 는 지역코드 , prefix, lineNum 세가지 핵심 정보를 담고있다.

        // 자동생성 toString
        // PhoneNumber{areaCode=9, prefix=213, lineNum=4244}
    }
}
