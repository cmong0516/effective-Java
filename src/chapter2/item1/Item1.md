# 생성자

~~~java
public class Car{
	// Car 클래스의 필드
	private final String name;
    private final int oil;
    // Car 클래스의 생성자
    public Car(String name, int oil){
    	this.name = name;
        this.oil = oil;
    }
}

// Car 클래스의 인스턴스를 만들기 위한 방법중 하나로 생성자를 이용한다면 아래처럼 사용할수 있다.
Car car1 = new Car("FirstCar", 100);
~~~

# 정적 팩터리 메서드란 ?
> - 우리가 일반적으로 클래스의 인스턴스를 얻기 위해 사용하는 것은 생성자 이다.
- 생정자 말고 인스턴스를 static 메서드를 활용해 생성할수 있는데 이것을 정적 팩터리 메서드 라고 한다.

# 정적 팩터리의 장점

## 1. 이름을 가지고 그 이름으로 의도를 표현할수 있다.

~~~java
public class Car{
	private final String name;
    private final int oil;
    
    // 생성자를 private 으로 설정해두었다.
    private Car(String name, int oil){
    	this.name = name;
        this.oil = oil;
    }
    
    // Car 클래스의 정적 메서드인 of 에서는 private인 생정자에 접근할수 있다.
    public static Car of(String name, int oil){
    	return new Car(name,oil);
    }
    
    // 위 메서드와 다른점은 이름과 oil 의 값이다.
    // 정적 팩터리 메서드의 첫번째 장점은 아래처럼 이름을 가질수 있고 그 이름으로 의도를 표현할수 있다 라는것이다.
    public static Car createNoOil(String name){
    	return new Car(name,0);
    }
}
~~~

## 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
~~~java
	// 미리 생성된 TRUE , FALSE
    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);
    
    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }
    
    // Boolean 의 valueOf 를 예시로 보면 valueOf 메서드가 실행될 때마다 새로운 인스턴스가 생성되는 것이 아니라 기존에 생성되어 있던 TRUE , FALSE 를 반환한다.
    // 반복되는 요청에 같은 객체를 반환하는 정적 팩터리 방식의 클래스는 언제 어느 인스턴스를 살아있게 할지를 철저히 통제할수 있고 이런 클래스를 인스턴스 통제 클래스 라고 한다.
    
    // 인스턴스를 통제하는 이유는 ?
    // 인스턴스를 통제하면 클래스를 싱글턴으로 만들수도 인스턴스화 불가 로 만들수도 있고 불변 클래스에서 동치인 인스턴스가 단 하나뿐임을 보장할수 있다.
~~~

## 3. 반환 타입의 하위 타입 객체를 반환할수 있다.
~~~java
	// 자바 8버전 이전에는 인터페이스 정적 메서드를 선언할수 없었는데 8버전 이후로는 가능해졌다.
    
    public class Account {
    // Account 의 생성자
	public Account() {}
	}

	public class AccountFactory {
    	// AccountFactory 의 정적 팩터리 메서드.
        // money 값에 따라 Vip , Normar 을 반환하는데 두 클래스 모두 Account 의 하위 타입이다.
		public static Account of(int money) {
			if(money > 100) {
				return new VipAccount();
			}
			else {
				return new NormalAccount();
			}
		}
	}

	public class VipAccount extends Account{
		public Vip() {}
	}

	public class NormalAccount extends Account{
		public Normal() {}
	}
    
  // 자바 9 부터는 private 정적 메서드까지 허락하지만 정적 필드와 정적 멤버 클래스는 public 이어야 한다.
~~~

## 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할수 있다.
~~~java
    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");

        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }
~~~

>-  EnumSet 클래스를 보면 public 생성자 없이 default 생성자만 존재하고 정적 팩토리 메서드를 적용하였는데 universe 의 크기에 따라 다른 객체를 반환한다.
- 클라이언트는 RegularEnumSet , JumboEnumSet 두 객체 정보를 알지 못한다.

## 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
>- 이런 유연함이 서비스 제공자 프레임워크 를 만드는 근간이 되며 대표적으로 JDBC 가있다.
- 서비스 제공자 프레임 워크에서의 제공자는 서비스의 구현체이며 이 구현체들을 클라이언트에 제공하는 역할을 프레임 워크가 통제하여 클라이언트를 구현체로부터 분리해준다.

~~~java
	public static void main(String[] args) {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "soyeon";
    
		try {
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url,user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
~~~
- DriverManager 클래스 관련된 코드가 없는데 어떻게 사용할수 있는것일까 ??
- ClassForName(driverNamne) 메서드를 통해 com.mysql.jdbc.Driver 클래스가 메모리에 로드된다.
- 메모리에 로드 되면서 com.mysql.jdbc.Driver 클래스의 static 절이 실행되고 DriverManager.registerDriver() 메서드를 실행시켜 자기 자신을 등록한다.
- 등록된 Driver 는 데이터베이스 Connection 을 생성하는 시점에서 사용되게 된다.
  JVM 이 동작을 시작하고 코드가 실행되기 전까지는 어떤 드라이버가 사용될지 모르기 때문에 동적으로 드라이버를 로딩하려면 리플렉션을 사용한다.

### 단점
>- 상속을 하려면 public 이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들수 없다.
- 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
  따라서 널리 알려진 규약에 따라 이름을 지어주자.

#### 정적 팩터리 메서드의 이름 규약
>- from : 매개 변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
- of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 메서드
- valueOf : from 과 of 를 더 자세히 표현한것
- instance , getInstance : 매개변수로 명시한 인스턴스를 반환하지만 같은 인스턴스 임은 보장하지 않는다.
- create , newInstance : instance , getInstance 와 같지만 매번 새로운 인스턴스를 생성해 반환함을 보장한다.
- getType : getInstance 와 같으나 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할때 사용.
- newType : newInstance 와 같으나 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할때 사용.
- type : getType 과 newType 과 같다.