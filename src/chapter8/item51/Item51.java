package chapter8.item51;

public class Item51 {
    // 메서드 시그니처를 신중히 설계하라.

    // 1. 메서드 이름 잘 짓기.
    // 2. 편의 메서드를 너무 많이 만들지 않기. (꼭 필요한 메서드만 만들자)
    // 3. 매개변수 목록은 짧게 유지하자.
        // 3-1 매개변수를 줄이는 방법.
        // 1. List 를 사용
        // 2. 매개변수를 줄여주는 도우미 클래스 사용 (정적 멤버 클래스)
        // 3. 빌더패 (매개변수가 많고 일부는 생략되어도 될때)
    // 4. 매개변수 타입으로는 클래스보다 인터페이스가 더 좋다.
    // 5. boolean 보다는 열거타입. ex) true ? hello : hi    ->    HELLO , HI


    // 편의메서드 EX1
    // 의도를 명확히 파악할수 있는 이름
    public int add(int a, int b) {
        return a+b;
    }

    // 편의메서드 EX2
    // 의도를 명확히 파악할수 있는 이름
    public String toUpperCase(String input) {
        return input.toUpperCase();
    }

    // 도우미 클래스 EX
    // 필요한 매개변수 rank , suit 이 있다면
    // Card 클래스 하나만 매개변수로 받으면 된다.
    static class Card {
        private final int rank;
        private final String suit;

        public Card(int rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return "rank : " + rank + " suit : " + suit;
        }
    }

    public static void main(String[] args) {
        Card card = new Card(4,"card1");
        System.out.println(card.toString());

    }

}
