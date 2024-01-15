# 싱글턴
>- 싱글턴이란 인스턴스를 오직 하나만 생성할수 있는 클래스.

## 싱글턴을 만드는 방법

### 1. public static final
~~~java
  public class Singleton {
      public static final Singleton1 INSTANCE = new Singleton();

      private Singleton() {
	      if(this.INSTANCE != null){
          	throw new IllealStateException();
          }
      }

      public void run() {
          System.out.println("싱글톤");
      }
  }
~~~
>- private 생성자는 Singleton.INSTANCE 를 초기화 할때 한번만 호출된다.
- public 이나 protected 생성자가 없으므로 클래스가 초기화 될때 만들어진 인스턴스가 전체 시스템에서 하나뿐이다.
- 단 하나 예외는 AccessibleObject.setAccessible 을 사용해서 private 생성자를 호출하는것.
-  이 예외를 차단하려면 생성자 내부에 두번째 객체가 생성될때 예외를 던진다.
- API 에서 싱글턴임이 명백히 드러나며 public static 필드가 final 이므로 절대 다른 객체를 참조할수 없다.

### 2. 정적 팩터리 메서드를 public static 멤버로 제공.
~~~java
  public class Singleton {
      private static final Singleton INSTANCE = new Singleton();

      private Singleton() {
      }

      public static Singleton getInstance() {
          return INSTANCE;
      }

      public void run() {
          System.out.println("싱글톤");
      }
  }
  
  // 제네릭 싱글턴 팩터리 패턴
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }
~~~
>- Singleton.INSTANCE 는 항상 같은 객체를 참조해 반환한다.
- 정적 팩터리를 제네릭 싱글턴 팩터리로 만들수 있다.
- 정적 팩터리의 메서드 참조를 공급자 (Supplier) 로 사용할수 있다.

### 3. 원소가 하나인 열거타입
~~~java
public enum Singleton{
	INSTANCE;
}
~~~
>- 만들려는 싱글턴이 Enum 이외의 클래스를 상속해야 한다면 사용할수 없다.