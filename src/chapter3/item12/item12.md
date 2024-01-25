# toString
>- Object 의 기본 toString 메서드가 우리가 작성한 클래스에 적합한 문자열을 반환하는 경우는 거의 없다.
   (주소값을 알려줌 -> 클래스이름@16진수의 해시코드)
- toString 의 일반규약 : 간결하면서 사람이 읽기 쉬운 형태의 유익한 정보를 반환해야 한다.
- 모든 하위 클래스에서 이 메서드를 재정의하라.
- toString 을 잘 구현한 클래스는 디버깅하기 더 쉽다.

~~~java
@Override public String toString(){
	return String.format("03d-%03d-%04d", areaCode, prefix , lineNum);
}
~~~

# toString 재정의가 필요없는 경우
## 1. 정적 유틸리티 클래스
>- 정적 유틸리티 클래스는 객체를 만들어 사용하지 않으므로 toString을 재정의 할 필요가 없다,

## 2. 열거타입
~~~java
public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {
    private final String name;
    // ...
    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }
    // ...
    public String toString() {
        return name;
    }
}
~~~
>- 추상클래스 Enum 은 toString정의가 되어있다.

>- 단 하위클래스 들이 공유해야할 문자열이 포함된 경우라면 toString을 재정의 해주어야 한다.
- 대다수의 컬렉션 구현체는 추상 컬렉션 클래스들의 toString 메서드를 상속해 사용한다.