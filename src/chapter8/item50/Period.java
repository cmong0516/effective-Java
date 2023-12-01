package chapter8.item50;

import java.util.Date;

public class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        // 메서드 매개변수 검증.
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }

//        this.start = start;
//        this.end = end;

        // 복사본 사용하기
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }

        // 복사본을 사용하고 그후 검증을 한다. vs 검증을 하고 복사본을 사용한다.
        // 멀티스레딩 환경이라면 원본 객체의 유효성을 검사한 후 복사본을 만드는 순간에도 취약한 부분이 존재.
        // Date 는 final 이 아니므로 clone 을 사용하면 안된다.
        // ? : Date 는 확장될수 있기 때문.
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }


    // 캡슐화를 통한 은닉이 되어있고 생성자에서 매개변수 검증도 하며 set메서드 등의 내부를 변화시킬 위험이 없어보인다.
    // 하지만 Date 자체가 가변이다.

    // getStart() , getEnd() 의 방어적 프로그래밍 방법.

    public Date getStart2() {
        // return start.clone();
        return new Date(start.getTime());
    }

    public Date getEnd2() {
        // return end.clone();
        return new Date(end.getTime());
    }

    // 복사본을 반환해서 원본을 변화시킬수 없도록 하였다.
    // start , end 는 Date 가 확실하므로 여기에선 clone() 을 사용해도 된다.
    // 일반적으로는 생성자  , 정적 펙터리를 쓰는게 좋다.


    @Override
    public String toString() {
        return "start : " + start + " end : " + end;
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();

        Period period = new Period(start, end);

        System.out.println("period = " + period);

        end.setYear(78);

        System.out.println("period = " + period);

        // 물론 Date 는 LocalDateTime , ZonedDateTime 을 사용하면 된다.
        // 예시일뿐.
        // 이처럼 가변 매개변수를 받을경우 방어적으로 복사하고 Period 인스턴스 내부에선 복사본을 사용한다.

        Date start2 = new Date();
        Date end2 = new Date();

        Period period2 = new Period(start2, end2);

        System.out.println("period2 = " + period2);

        period2.getEnd().setYear(78);

        System.out.println("period2 = " + period2);

        // 두번째 공격.
        // 접근자 메서드가 내부의 가변 정보를 직접 드러낸다.
        // 가변 필드의 방어적 복사본을 반환해야 한다.
    }
}
