package chapter6.item34;

public enum PayrollDay {
//    MONDAY, TUESDAY, WEDNESDAY,
//    THURSDAY, FRIDAY,
//    SATURDAY, SUNDAY;

    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

//    private final int MINS_PER_SHIFT = 8 * 60;

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }
    // PayrollDay() { this(PayType.WEEKDAY); } // (역자 노트) 원서 4쇄부터 삭제

//    int pay(int minutesWorked, int payRate) {
//        int basePay = minutesWorked * payRate;
//
//        int overtimePay;
//        switch (this) {
//            // 주말만 체크했다.
//            // 그럼 주말이 아닌 공휴일에 근무를 하게되면 평일과 같은 임금을 받게된다.
//            case SATURDAY: case SUNDAY:
//                overtimePay = basePay/2;
//                break;
//            default:
//                overtimePay = minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
//        }
//
//        return basePay + overtimePay;
//    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }


    enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
