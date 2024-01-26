# Comparable
~~~java
public interface Comparable<T> {
    public int compareTo(T o);
}
~~~

>- Comparable 인터페이스에 들어가보면 제네릭이 적용된 int 타입을 반환하는 compareTo 메서드 하나만 있다.
- compareTo 는 Object 의 메서드가 아니지만 성격 Object 의 equals 와 비슷하다.
- equals 와의 차이점 : compareTo 는 단순 동치성 비교에 더해 순서까지 비교할수 있으며 제네릭하다.
- Compareable 을 구현했다면 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 뜻하며 Comparable 을 구현하 객체들의 배열은 정렬할수 있다. (Arrays.sort())
- 검색 ,극단값 계산 , 자동정렬 되는 컬렉션 관리역시 쉽게 할수 있다.

~~~java
public class WordList{
	public static void main(String[] args){
    	Set<String> s = new TreeSet<>();
        Collections.addAll(s,args);
        sout(s);
    }
}
~~~
>- 자바 플랫폼 라이브러리의 모든 값 클래스와 열거타입이 Comparable 을 구현했다.
- 알파벳 , 숫자 , 연대 같이 순서가 명확한 값 클래스를  작성한다면 반드시 Comparable 을 구현하자.


# compareTo 메서드이 일반 규약
>- Comparable 을 구현한 클래스는 모든 x,y 에 대해 sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) 여야한다.
   x.compareTo(y) 는 y.compareTo(x) 가 예외를 던질때 예외를 던져야 한다.
- Comparable 을 구현한 클래스는 추이성을 보장해야 한다.
  x.compareTo(u) > 0 && y.compareTo(z) > 0 이면 x.compareTo(z) > 0 이다.
- Compable 을 구현한 클래스는 모든 z 에 대해 x.compareTo(y) == 0 이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z) 다.
- (x.compareTo(y) == 0) == (x.equals(y)) 여야한다.

# equals() 와의 차이점
>- Comparable 은 타입을 인수로 받는 제네릭 인터페이스이므로 compareTo 메서드의 인수 타입은 컴파일타임에 정해진다.
   -> 입력 인수의 타입을 확인하거나 형변환 할필요가 없다.
- compareTo 메서드는 각 필드가 동치인지를 비교하는 것이 아니라 그 순서를 비교하기 때문에 객체 참조 필드를 비교하려면 compareTo 메서들 재귀적으로 호출한다.
- equals 에서 비교할때 핵심 필드를 모두 비교한다면 compareTo 는 어느것을 먼저 비교하느냐가 중요한데 가장 핵심적인 필드부터 비교해야한다.

~~~java
public int compareTo(PhoneNumber pn){
	int result = Short.compare(areaCode , pn.areaCode);
    if(result == 0){
    	result = Short.compare(prefix , pn.prefix);
        if(result == 0){
        	result = Short.compare(lineNum , pn,lineNum);
        }
    }
}
~~~

## 정적 비교자 생성 메서드를 사용하기
~~~java
private static final Comparator<PhoneNumber> COMPARATOR =
	comparingInt((PhoneNumber pn) -> pn.areaCode)
    	.thenComparingInt(pn -> pn.prefix)
        .thenComparingInt(pn -> pn.lineNum);
        
public int comparTo(PhoneNumber pn){
	return COMPARATOR.compare(this , pn);
}
~~~

# 주의
~~~java
static Comparator<Object> hashCodeOrder = new Comparator<>(){
	public int compare(Object o1 , Object o2){	
    	return o1.hashCode() - o2.hashCode();
    }
}
~~~

>- 값의 차를 기준으로 음수 , 0 , 양수 를 기준으로 정렬여부를 나타내는 이방식은 정수오버플로를 일으키거나 부동소수점 계산방식에 따른 오류를 낼수 있다.
- 결과자체도 비교자 생성 메서드를 활용한 방법보다 월등히 빠르지 않다.

## 개선

### 1.
~~~java
static Comparator<Object> hashCodeOrder = new Comparator<>(){
	public int compare(Object o1, Object o2){
    	return Integer.compareTo(o1.hashCode() , o2.hashCode());
    }
}
~~~

### 2.
~~~java
static Comparator<Object> hashCodeOrder = Comparator.comparingInt(o -> o.hashCode());
~~~