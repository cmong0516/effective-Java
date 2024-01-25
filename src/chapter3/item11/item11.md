>- equals 를 재정의 한 클래스 모두에서 hashCode() 도 재정의 해야 HashMap , HAshSet 같은 컬렉션 원소로 클래스를 사용할수 있다.

# Object 명세서의 equals , hashCode

- equals 비교에 사용되는 정보가 변경되지 않았다면 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇번을 호출해도 일관되게 같은 값을 반환해야 한다.
  (애플리케이션을 다시 실행할경우는 다른값이어도 된다.)
- equals 가 두 객체를 같다고 판단했을 경우 두 객체의 hashCode() 는 같은 값을 반환해야 한다.
- equals 가 두 객체를 다르다고 판단했더라도 두 객체의 hashCode 가 서로 다른 값을 반환할 필요는 없지만 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.


# 좋은 hashCode 를 작성하는법
>1. int 변수 result 를 선언한후 값 c 로 초기화한다. 이때 c는 해당 객체의 첫번째 핵심 필드를 단계 2.a 방식으로 계산한 해시코드이다.
2. 해당 객체의 나머지 핵심 필드 f 각각에 대해 다음 작업을 수행한다.
   a. 해당 필드의 해시코드 c를 계산한다.
- 기본 타입 필드라면 Type.hashCode(f) 를 수행한다.
- 참조 타입 필드면서 이 클래스의 equals메서드가 이 필드의 equals 메서드를 재귀적으로 호출해 비교한다면 이 필드의 hashCode 를 재귀적으로 호출한다.
  계산이 더 복잡해질것 같으면 이 필드의 표준형을 만들어 그 표준형의 hashCode 를 호출한다.
  필드값이 null 이면 0을 사용한다.
- 필드가 배열이라면 핵심 원소 각각을 별도 필드처럼 다룬다.
  이상의 규칙을 재귀적으로 적용해 각 핵심 원소의 해시코드를 계산한 다음 2.b 방식으로 갱신한다.
  배열에 핵심 원소가 하나도 없다면 0을 사용하고 모든 원소가 핵심 원소라면 Arrays.hashCode를 사용한다.
  b. 2.a 에서 계산한 해시코드 c 로 result 를 갱신한다. -> result = 31 * result +c;
3. result를 반환한다.

# 전형적인 hashCode 메서드

## 1.
~~~java
public final class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix   = rangeCheck(prefix,   999, "프리픽스");
        this.lineNum  = rangeCheck(lineNum, 9999, "가입자 번호");
    }
}
~~~


~~~java
@Override public int hashCode(){
	int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    
    return result;
}
~~~

## 2.
>- Objects 클래스는 임의의 개수만큼 객체를 받아 해시코드를 계산해주는 정적 메서드인 hash 를 제공한다.
- 하지만 이방식은 성능상으로 아쉬우므로 성능상 크게 문제가 되지 않을경우 사용하자.

~~~java
@Override public int hashCode(){
	return Objects.hash(lineNum , prefix , areaCode);
}
~~~

## 3.
>- 클래스가 불변이고 해시코드를 계산하는 비용이 크다면 매번 새로 계산하기 보다 캐싱하는 방식을 고려해야 한다.
- 해시의 키로 사용되지 않는 경우라면 hashCode 가 처음 불릴때 계산하는 지연 초기화 전략.
- hashCode 필드의 초기값은 흔히 생성되는 객체의 해시코드와는 달라야 함을 주의하자.

~~~java
private int hashCode;

@Override public int hashCode(){
	int result = hashCode();
    if(result == 0){
    	result = Short.hashCode();
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        hashCode = result;
    }
}
~~~


# 결론
>- 성능을 높인다고 해시코드를 계산할때 핵심 필드를 생략해서는 안된다.
- hashCode 가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 말자.
  (그래야 클라이언트가 이 값에 의지하지 않게 되고 추후 계산방식을 변경할수 있다.)