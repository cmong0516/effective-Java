package chapter9.item60;

import java.math.BigDecimal;

public class Item60 {
    // 정확한 답이 필요하다면 float 와 double 은 피하라.

    // float 와 double 은 공학 계산용으로 이진 부동소수점 연산에 쓰이며 넓은 범위의 수를 빠르고 정밀하게 근사치로 계산하도록 설계되었다.
    // 근사치 를 계산하게 설계되었기 때문에 정확한 값을 구하려고 한다면 사용하지 않아야 한다.

    public static void main(String[] args) {
        System.out.println(1.03 - 0.42);
        // 0.6100000000000001

        System.out.println(1.00 - 9 * 0.10);
        // 0.09999999999999998

        // 잘못된 예
//        double funds = 1.00;
//        int itemBought = 0;
//
//        for (double price = 0.10; funds >= price; price += 0.10) {
//            funds -= price;
//            itemBought++;
//        }
//
//        System.out.println(itemBought + "개 구입");
//        System.out.println("잔돈(달러) :" + funds);

        // 금융 계산을 할때 . 위에서 float , double 은 근사치를 계산하지 정확한 값이 아니라고 했다.
        // 금융계산 공학계산 등은 오차에 민감한 아주 정밀한 계산을 필요로 하는 작업이다.
        // 따라서 BigDecimal, int , long 을 사용해야 한다.

        // BigDecimal 사용.
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");

        for (BigDecimal price = TEN_CENTS;
             funds.compareTo(price) >= 0;
             price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);

        // BigDecimal 사용은 결과가 정확하게 나오지만 기본 타입보다 쓰기가 불편하며 느리다.
        // int  , long 을 사용하자니 수의 범위가 제핟되고 소수점을 직접 관리해야한다.
    }
}
