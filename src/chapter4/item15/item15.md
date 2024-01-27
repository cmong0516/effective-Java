# 캡슐화의 장점
>- 여러 컴포넌트를 병렬로 개발할수 있기 때문에 시스템 개발 속도를 높인다.
- 각 컴포넌트를 더 빨리 파악하여 디버깅 할수 있고 다른 컴포넌트로 교체하는 부담도 적어 시스템 관리 비용을 낮출수 있다.
- 다른 컴포넌트에 영향을 주지 않고 컴포넌트를 최적화 할수 있기때문에 성능 최적화에 도움을 준다.
- 외부에 거의 의존하지 않고 독자적으로 동작할수 있는 컴포넌트 라면 낯선 환경에서도 유용하게 쓰일수 있으며 재사용성을 높일수 있다.
- 시스템 전체가 완성되지 않은 상태에서도 동적 검증을 할수 있기때문에 큰 시스템을 제작하는 난이도를 낮춰준다.


# 접근제어자
>- 모든 클래스와 멤버의 접근성은 소프트웨어가 올바르게 동작하는 가장 낮은 접근 수준으로 해야한다.
1. public : 모든 곳에서 접근할수 있다.
2. package-private : 멤버가 소속된 패키지 안의 모든 클래스에서 접근할수 있다. (접근 제한자를 명시하지 않았을대 적용되는 패키지 접근 수준 , Interface 멤버는 public)
3. private : 멤버를 선언한 톱레벨 클래스 에서만 접근할수 있다.
4. protected : package-private 의 접근범위를 포함하며 선언한 클래스의 하위 클래스에서도 접근할수 있다.
- 상위 클래스의 메서드를 재정의 할때 그 접근 수준을 상위 수준의 클래스보다 좁게 설정할수 없다.
  상위 클래스는 하위 타입으로 대체할수 있어야 하는 리스코프 치환 원칙


# 배열
>- 길이가 0 이 아닌 배열은 모두 변경이 가능하다.
   따라서 클래스에서 public static final 배열필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공해서는 안된다.
   이런 필드나 접근자를 제공하게 되면 클라이언트에서 그 배열의 내용을 수정할수 있다.

~~~java
public static final Thing[] values = {...};
// public static final 필드가 가변인 배열이다.
~~~

## 개선1
~~~java
private static final Thing[] PRIVATE_VALUES = {...};
public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
~~~

## 개선2
~~~java
private static final Thing[] PRIVATE_VALUES = {...};
public static final Thing[] values(){
	return PRIVATE_VALUES.clone();
}
~~~


# 결론
> 프로그램 요소의 접근성은 가능한 최소한으로 하고 꼭 필요한 것만 골라 최소한의 public API 를 설계하자.
public 클래스는 상수용 public static final 필드 외에는 어떤한 public 필드도 가져서는 안된다.
public static final 필드가 참조하는 객체가 불변인지 확인해야 한다.
