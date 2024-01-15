# 객체 생성
>필요한 객체는 당연히 생성하여 사용해야 한다.
하지만 객체 생성이란 리소스를 소모하는 일이고 꼭 필요한 최소한의 객체만을 생성하여 사용하면 효율적인 리소스 관리를 할수 있다.

# 잘못된 예시

## Long

~~~java
    private static long sum() {
        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
~~~
- Long 은 Warpper 타입 이므로 기본형인 long 으로 사용한다면 더 효율적인 동작이 가능하다.

## String

### 1. 리터럴
> 이 방식으로 선언한 String 은 Heap 영역의 String pool 에 저장되며 == 연산자로 값을 비교할수 있다.

### 2. new
> new 로 생성한 String 은 객체로써 String pool 이 아닌 Heap 영역에 저장되어 관리된다.
동등성을 비교할때 == 을 사용할수 없고 eqauls() 를 사용해야한다.

## 차이점
~~~java
String s = "abcd";
String s2 = "abcd";
// 위의 경우 s == s2 의 값이 true 가 된다.
// Heap 영역의 String pool 에서 "abcd" 가 갖는 주소값을 공유하기 때문이다.

String s3 = new String("aaaa");
String s4 = new String("aaaa");
// 위의 경우는 ==연산자로 동등성 비교를 할수 없다.
// Heap 영역에 각각의 다른 객체로서 다른 주소값을 가지기 때문이다.

~~~

## Pattern
~~~java
static boolean isRomanNumeral(String s){
	return s.matches("^(?=.)M*(C[MD]|D?C{0,c})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}
~~~
>- 주어진 문자열이 유효한 로마 숫자인지를 확인하는 메서드로 정규표현식을 사용하여 matches() 메서드로 true , false 값을 반환한다.
- String , matches 는 정규표현식 문자열 형태를 확인하는 가장 쉬운 방법이지만 성능이 중요한 상황에서 반복해 사용하기엔 적합하지 않다.
- Pattern 인스턴스는 한번 쓰고 버려져 GC 대상이 되는데 입력받은 정규표현식에 해당하는 윻나 상태 머신을 만들기 때문에 인스턴스 생성 비용이 높다.

## 개선
~~~java
public class RomanNumerals{
	private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,c})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    
    static boolean isRomanNumeral(String s){
    	return ROMAN.matcher(s).matches();
    }
}
~~~
>- Pattern 인스턴스를 한번 생성해두고 재사용하도록 변경하여 여러번 사용시에도 객체를 재 생성하는 문제에서 해결되었다.


# 결론
> 어떤 객체를 반복적으로 사용할때 객체를 효율적으로 생성하여 사용하고 있는지를 체크해보고 그렇지 않다면 아낄수 있는 리소스를 사용하고 있으니 수정하면 좋다.