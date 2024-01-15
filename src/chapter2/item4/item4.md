# private 생성자
~~~java
public class UtilityClass {
	private UtilityClass(){
    	throw new AssertionsError();
    }
}
~~~
>- 생성자의 접근 제어자를 private 으로 설정하면 클래스의 인스턴스화를 막을수 있다.
- private 생성자에 접근해서 실행한다고 하더라도 실행하면 AssertionsError 가 발생하게 된다.
- 이렇게 작성된 클래스는 어떤 환경에서도 인스턴스화 할수 없다.

# 이런걸 어디쓰나 ?
>java.lang.Math 의 인스턴스를 얻을수 있는가 ?
java.util.Arrays 의 인스턴스를 얻을수 있는가 ?
- 기본 타입 값이나 유틸 메서드 들을 모아놓은 클래스에 사용할수 있다. 