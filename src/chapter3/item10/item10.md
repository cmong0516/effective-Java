# equals()
>- equals() 메서드는 객체의 동일성 여부를 판단하는 메서드이다.
- == 은 주소값을 비교하는 call by reference , equals() 는 값을 비교하는 call by value
- equals() 가 동등성 여부를 비교하게 하기 위해서 값을 비교하여 올바르게 동작하도록 재정의 해주어야 한다.
- equals 로 동등성 여부를 검사하게 될경우 동일한 객체는 동일한 메모리 주소를 가져야 하므로 동일한 해시코드를 가져야 하기 때문에 equals() 메서드를 재정의 할경우 hashCode() 메서드도 재정의 해주어야 올바른 동등성 여부를 검사하는 것이다.

## 각 인스턴스가 본직적으로 고유하다.
>- 값을 표현하는게 아니라 동자갛는 개체를 표현하는 클래스에 해당하는 것으로 Thred 가 좋은 예로 Object의 equals 메서드는 이런 클래스에 맞게 구현되었다.

## 인스턴스의 논리적 동치성을 검사할 일이 없다.
>- Pattern은 equals 를 재정의 해서 두 Pattern 의 인스턴스가 같은 정규표현식을 나타내는지를 검사하는 논리적 동치성을 검사하는데 이런 방식이 필요하지 않을경우 Object 의 기본 equals 로 해결된다.

## 상위 클래스에서 재정의한 equals 가 하위 클래스에도 들어맞는다.
>- Set구현체인 AbstractSet 이 구현한 equals 를 상속받아 쓰고 List 구현체들은 AbstractList , Map 구현체들은 AbstractMap 의 equals 를 그대로 상속받아 쓴다.

## 클래스가 private 이거나 package-private 이고 equals 메서드를 호출할 일이 없다.
~~~java
@Override public boolean equals(Object o){
	throw new AssertionError();
}
~~~
>- equals 가 호출되는 것을 막고싶다면 호출시 에러가 발생하도록 만들수 있다.

# 재정의

>- equals 를 재정의 할때는 객체 식별성이 아니라 동치성을 확인해야 하는데 상위 클래스의 equals 가 논리적 동치성을 비교하도록 재정의 되지 않았을 때로 값 클래스들이 주로 여기 해당한다.
- equals 가 논리적 동치성을 확인하도록 재정의 해두어야 Map 의 키 , Set 의 원소로 사용할수 있게 된다.
- 값 클래스라 해도 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 통제 (싱글턴) 클래스라면 equals 는 객체 식별성과 논리적 동치성이 일치하므로 재정의 하지 않아도 된다.

## equals 메서드 재정의 규약
>- 반사성 : null 이 아닌 모든 참조값 x 에 대해 x.quals(x) 는 true 이다.
   객체는 자기 자신과 같아야 한다.
- 대칭성 : null 이 아닌 모든 참조값 x,y 에 대해 x.equals (y) 가 true 이면 y.equals(x) 도 true 다.
  두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다.
- 추이성 : null 이 아닌 모든 참조값 x,y,z 에 대해 x.equals(y) 가 true 이고 y.equals(z) 도 true 이면 x.equals(z) 도 true 다.
  A 와 B 가 같고 B 와 C 가 같다면 A 와 C 도 같아야한다.
- 일관성 : null 이 아닌 모든 참조값 x,y 에 대해 x.equals(y) 를 반복해서 호출하면 항상 true or false 를 반환한다.
  두 객체가 같다면 앞으로도 영원히 같아야 한다.
- null-아님 : null 이 아닌 모든 참조값 x 에 대해 x.equals(null) 은 false 다.
  모든 객체가 null과 같지 않아야 한다.

# 올바른 재정의 순서
>1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다
2. instanceof 연산자로 입력이 올바른 타입인지 확인한다
3. 입력을 올바른 타입으로 형변환 한다
4. 입력 객체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사한다.(다를 가능성이 크거나 비교하는 비용이 싼 필드를 먼저)