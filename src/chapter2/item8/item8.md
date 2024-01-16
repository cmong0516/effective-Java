# 객체 소멸자
>- 자바는 finalizer 와 cleaner 두가지 객체 소멸자를 제공한다.
- finalizer 는 예측할수 없고 상황에 따라 위험할수 있어서 불필요하며 오동작 , 낮은 성능 , 이식성 문제의 원이이 되기도 하여 9 버전 이후 deprecated 되었다.
- cleaner 는 finalizer 보단 덜 위험하지만 예측할수 없고 느리고 일반적으로는 불필요하다.
- 객체 소멸자를 사용하기 보다는 try-with-resources , try-finally 를 사용하자.
- java 의 객체 소멸자는 언제 실행될지 GC 구현마다 다르고 자바 언어 명세에서도 수행 시점과 수행 여부를 보장하지 않는다.
- finalizer 를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬수도 있다.
- finalizer  ,cleaner 는 많은 단점을 가지고 있는데 이를 대신하기 위해 AutoClosable 을 구현해서 인스턴스를 다 사용하고 나면 close 메서드를 호출하자.

# 언제 사용하는가 ?
>- 위에서 언급한 내용들을 보면 두 객체 소멸자는 단점만을 가지고 있는거같지만 두가지 적절한 쓰임새가 있다.
1. 자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 안전망
   (두 객체 소멸자가 언제 실행될지 , 실행 되긴 할지 알수 없지만 클라이언트가 회수하지 않은 자원을 실행된다면 회수할수 있다.)
2. 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체는 자바 객체가 아니기 때문에 GC가 감지할수 없는데 이때 객체 소멸자를 사용한다.

# Ex

~~~java
public class Room implements AutoCloseable {
    // cleaner 를 안전망으로 활용하는 AutoCloseable
    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원. 절대 Room 을 참조해서는 안된다.
    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    private final State state;

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        this.state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}

~~~
>- static 으로 선언된 중첩 클래스인 State 는 cleaner 가 방을 청소할때 수거할 자원들을 담고있다.
   (방 안의 쓰레기 수를 뜻하는 numJunkPiles)
   State가 중첩 클래스인 이유는 State 가 Rom 인스턴스를 참조할 경우 순환참조가 생겨 GC가 Room 인스턴스를 회수할수 없다.
- State 는 Runnable 을 구현하고 그 안의 run 메서드는 cleanable 에 의해 딱 한번 호출된다.
- cleanable 객체는 Room 생성자에서 cleaner 에 Room , State 를 등록할때 얻는다.

# 개선책
~~~java
public class Adult{
	public static void main(String[]args){
    	try(Room myRoom = new Room(1)){
        	System.out.println("Room1");
        }
    }
}
~~~

>- Room1을 출력하고 Cleaning room 을 출력한다.
- try-with-resource 를 사용하여 자동으로 자원회수를 하게 되며 clean() 메서드가 실행되어 state 를 회수할때 run 메서드가 실행된다.