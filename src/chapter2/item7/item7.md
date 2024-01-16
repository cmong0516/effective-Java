# 가비지 컬렉터(GC)
>- 자바는 직접 메모리 관리를 하지 않고 가비지 컬렉터 가 참조 해제된 객체들을 수거하여 메모리를 관리해준다.
- 가비지 컬렉터가 동작할때 다른 스레드는 동작을 멈추고 참조여부를 확인,수거 하는 작업을 거친후 다른 스레드들이 동작을 재개한다.

# 메모리 누수

~~~java
public class Stack {
    private Object[] elements;
    private int size = 0;

    public Stack() {
        elements = new Object[10];
    }

    public void push(Object o) {
        ensureCapacity();
        elements[size++] = o;
    }

    // pop 을 해서 사용하지 않게된 객체들이 여전히 참조되고 있다.
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    // GC는 객체 참조 하나를 살려두면 그 객체가 참조하는 모든 객체를 회수해가지 못한다.
}

~~~
>- 위 스택을 사용하는 프로그램을 오래 실행하다 보면 GC 활동과 메모리 사용량이 늘어나 결국 성능이 저하된다.
- 스택이 커졌다가 줄어들때 스택에서 꺼내진 객체들을 더이상 사용하지 않는다고 해도 가비지 컬렉터가 회수하지 않는데 이 스택이 그 객체들의 다쓴 참조를 가지고 있기 때문이다.
- 참조 여부를 체크하여 메모리를 확보하는 GC 가 보기에 참조되어 있기 때문에 이 객체들은 수거되지 않고 사용도 되지 않고있다.

# 해결책

~~~java
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];

        // 참조 해제!
        elements[size] = null;
        return result;
    }
~~~
>- 다 쓴 참조를 null 처리하여 참조 해제를 해주었다. 하지만 이 방법은 사용한 모든 객체를 하나하나 null 처리해야 하는 번거로움이 발생한다.
- 다쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 유효 스코프 밖으로 밀어내는 것이며 변수의 범위는 최소화 하는것이 가장 좋은데 이를 지켰다면 자연스럽게 진행할수 있다.
- Stack 클래스는 배열로 저장소 풀을 만들어 원소들을 관리하는데 배열의 비활성 영역은 쓰이지 않는데 GC는 이를 모르기 때문에 비활성 영역에서 참조하는 객체도 유효하다고 판단하게 된다.