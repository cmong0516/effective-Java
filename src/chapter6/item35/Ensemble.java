package chapter6.item35;

public enum Ensemble {
//    SOLO, DUET, TRIO, QUARTET, QUINTET,
//    SEXTET, SEPTET, OCTET, DOUBLE_QUARTET,
//    NONET, DECTET, TRIPLE_QUARTET;

    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble(int size) {
        this.numberOfMusicians = size;
    }

    //    public int numberOfMusicians() {
    // 해당 상수가 몇번째에 위치하는지를 제공하는 메서드 ordinal();
//        return ordinal() +1;
//    }

    public int getNumberOfMusicians() {
        return numberOfMusicians;
    }

    public static void main(String[] args) {
//        int i = SOLO.numberOfMusicians();
//        System.out.println("i = " + i);
//        // 1
//
//        int i1 = DUET.numberOfMusicians();
//        System.out.println("i1 = " + i1);
//        // 2

        // 동작은 하지만 유지보수 하기가 힘들다.
        // 상수 선언 순서를 바꾸면 메서드가 원하는대로 동작하지 않을수 있다.

        int numberOfMusicians1 = NONET.getNumberOfMusicians();
        System.out.println("numberOfMusicians1 = " + numberOfMusicians1);
    }
}
