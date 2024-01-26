# Cloneable
>- Cloneable 은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스 이지만 의도한 목적을 제대로 수행하지 못했다
   clone 메서드가 선언된 곳이 Cloneable 이 아닌 Object 이고 protected이기 때문인데 Cloneable 을 구현해도 외부에서 clone 메서드를 호출할수가 없다.
- 메서드 하나 없는 Cloneable 인터페이스의 역할은 Object 의 protected 메서드인 clone 의 동작방식을 결정한다.
  clone 메서드를 호출하면 Cloneable 을 구현한 객체의 필드들을 하나하나 복사한 객체를 반환하며 그렇지 않은 클래스의 인스턴스에서 호출하면 CloneNotSupportedException 을 던진다.
- 실무에서 Cloneable 을 구현한 클래스는 clone 메서드를 public 으로 제공하며 사용자는 당연히 복제가 제대로 이루어질거라 생각하지만 그렇게 동작하기 위해서는 모든 상위 클래스의 복잡하고 강제할수 없으며 허술한 프로토콜을 지켜야만 한다.

# clone()구현
~~~java
@Override public PhoneNumber clone(){
	try{
    	return (PhoneNumber) super.clone();
    }catch(CloneNotSupportedException e){
    	throw new AssertionError();
    }
}
~~~
>- 이 메서드가 동작하게 하려면 PhoneNumber 클래스 선언에 Cloneable 을 구현한다고 추가해야 한다.(implement Cloneable)
- Object 의 clone메서드는 Object를 반환하지만 PhoneNumber 의 clone 메서드는 PhoneNumber 를 반환하게 했다.
- super.clone() 을 try-catch 로 감싼 이유는 Object 의 clone 메서드가 검사예외를 던지도록 선언되었기 때문.(CloneNotSupportedException)

# 가변객체를 참조할때

~~~java
public class Stack13 implements Cloneable{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack13() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object obj) {
        ensureCapacity();
        elements[size++] = obj;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

~~~

>- 이 클래스의 clone 메서드가 super.clone() 의 결과를 그대로 반환한다면 Stack 인스턴스의 size 필드는 올바른 값을 가지지만 elements 필드는 원본 Stack 인스턴스와 똑같은 배열을 참조하므로 프로그램이 이상하게 동작하거나 NullPointerException 을 던질것이다.
- clone 메서드는 생성자와 같은 효과를 내는데 원본 객체에 아무런 해를 끼치지 않아야 하며 복제된 객체들의 불변식을 보장해야 한다.

## clone()
~~~java
@Override public Stack clone(){
	try{
    // Object -> Stack
    	Stack result = (Stack) super.clone()
        result.elements = elements.clone();
        return result;
    }catch(CloneNotSupportedException e){
    	throw new AssertionError();
    }
}
~~~
>- Stack 의 clone 메서드가 제대로 동작하려면 내부 스택 정보를 복사해야 하는데 elements 배열의 clone 을 재귀적으로 호출해주면 된다.
- elements 필드가 final 이라면 필드에 새로운 값을 할당할수 없기때문에 이방식이 동작하지 않는데 Cloneable 아키텍처의 가변 객체를 참조하는 필드는 final 로 선언하라는 일반 용법과 충돌한다.

# 해시테이블
~~~java
public class HashTable implements Cloneable{
	private Entry[] buckets = ...;
    
    private static class Entry{
    	final Object key;
		Object value;
        Entry next;
        
        Entry(Object key, Object value, Entry next){	
        	this.key = key;
            this.value = value;
            this.next = next;
        }
        
    }
}
~~~

## clone()
~~~java
@Override public HashTable clone(){
	try{
    	HashTable result = (HashTable) super.clone();
        result.buckets = buckets.clone();
        return result;
    }catch(CloneNotSupportedException e){
    	throw new AssertionError();
    }
}
~~~

>- 복제본은 자신만의 버킷 배열을 갖지만 이 배열은 원본과 같은 연결 리스트를 참조한다.
   따라서 버킷을 구성하는 연결리스트를 복사해야 한다.

~~~java
public class HashTable implements Cloneable{
	private Entry[] buckets = ...;
    
    private static class Entry{
    	final Object key;
		Object value;
        Entry next;
        
        Entry(Object key, Object value, Entry next){	
        	this.key = key;
            this.value = value;
            this.next = next;
        }
        
        Entry deepCopy(){
        	return new Entry(key , value , next == null ? null : next.deepCopy();
        }
    }
    
    @Override public HashTable clone(){
	try{
    	HashTable result = (HashTable) super.clone();
        result.buckets = new Entry[buckets.length];
        for(int i = 0, i < buckets.length; i++){
        	if(buckets[i] != null){
            	result.buckets[i] = buckets[i].deepCopy();
            }
        }
       
        return result;
    }catch(CloneNotSupportedException e){
    	throw new AssertionError();
    }
}

}
~~~

>- private 클래스인 HashTable.Entry 는 deepCopy 를 지원하도록 보강되었다.
- clone 메서드는 먼저 적절한 크기의 새로운 버킷 배열을 할당한후 원래 버킷 배열을 순회하며 각 버킷에 대해 deepCopy 를 수행.
- Entry 의 deepCopy 메서드는 자신이 가리키는 연결리스트 전제츨 복사하기 위해 자기 자신을 재귀적으로 호출한다.
- 버킷이 너무 길지 않다면 잘 동작하겠지만 재귀 호출로 인해 리스트의 원소 수만큼 스택 프레임을 소모하게 되어 리스트가 길면 스택오버플로우가 일어날수 있다.

## deepCopy() 개선
~~~java
// Entry 자신이 가리키는 연결 리스트를 반복적으로 복사,
Entry deepCopy(){
	Entry result = new Entry(key , value, next);
    for(Entry p = result; p.next != null; p= p.next){
    	p.next = new Entry(p.next.key , p.next.value, p.next.next)
    }
    return result;
}
~~~


# 결론
>Cloneable 을 제대로 사용하려면
Cloneable을 구현하는 모든 클래스는 clone을 재정의
접근 제한자는 public으로
반환타입은 클래스 자신으로
super.clone을 호출 한 후 필요한 필드를 적절히 수정
이후 deepCopy
기본 타입 필드, 불변 객체 참조만 갖는 클래스면 아무 필드도 수정할 필요가 없다.
고유 ID는 수정해야한다.
등등 신경써야할 사항들이 너무 많은데 Cloneable 을 이미 구현한 클래스를 확장하는 경우가 아니라면 복사 생성자와 복사 팩터리 라는 방식이 있다.

# 복사 생성자
~~~java
public Yum(Yum yum){
	return new Yum();
}
~~~

# 복사팩터리
~~~java
public static Yum newInstance(Yum yum){	
	return new Yum();
};
~~~

>- 위의 두 방식을 보면 일반적인 생성자나 팩터리 메서드와 많이 다르다.
- 반환할 객체와 같은 객체를 인자로 받는다.
- 복사 생성자와 복사 팩터리 메서드를 잘 정의하면 Cloneable 을 구현하여 clone 메서드를 재정의 하는 과정에서 생각해야할 많은 문제를 줄여주면서 clone 메서드와 같은 효과를 낼수 있을것같다.
- 복사 생성자와 복사 팩터리는 해당 클래스가 구현한 인터페이스 타입의 인스턴스를 인수로 받을수 있다.