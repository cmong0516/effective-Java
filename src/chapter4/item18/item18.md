# 컴포지션과 상속

## 상속
>- 상속은 코드를 재사용하는 강력한 수단이지만 최선은 아닐수도 있다.
- 상속은 캡슐화를 깨트린다.

### EX)
~~~java
public class InstrumentedHashSet<E> extends HashSet<E>{
	// 추가된 원소 수
	private int addCount = 0;
    
    // 기본생성자
    public InstrumentedHashSet(){}
    
    // 생성자 오버로딩
    public InstrumentedHashSet(int initCap , float loadFactor){
    	super(initCap , loadFactor);
    }
    
    @Override public boolean add(E e){
    	addCount++;
        return super.add(e);
    }
    
    @Override public boolean addAll(Collection<? extends E> c){
    	addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount(){
    	return addCount;
    }
}
~~~

### 실행
~~~java
InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("틱","탁탁", "펑"));
~~~

> 크기 3의 List 를 addAll 하였으므로 addCount 는 3이 될거라고 생각하지만 실제로는 6을 반환한다.

### 왜 ?
> HashSet 클래스는 AbstractSet 을 상속하며 Set , Cloneable , Serializable 을 구현하는데 어디를 찾아봐도 addAll 메서드가 어떻게 작성되어있는지 나오지 않았다.
HashSet 클래스에서 addAll 메서드로 검색해서 얻을수 있는 코드는 아래 코드.

~~~java
    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }
~~~

> 위 addAll 을 타고 가보니
Collection 을 구현한 추상클래스 AbstractCollection 의 addAll() 메서드가 나오는데 반복문을 돌며 add 메서드를 사용하고있다.

~~~java
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }

~~~

>addAll 자체가 add 로 이루어졌다면 addAll + add 한 값이 중복 카운트 되어서 라고 추측해볼수 있다.
문제 해결은 addAll 메서드를 재정의 하여 add 메서드가 중복호출되지 않게 하면 되지만 HashSet 내부에 addAll 메서드가 어떻게 동작하는지 나와있지 않기 때문에 현재처럼 그럴것이다 라는 추측으로만 해결을 하게되었다. (HashSet 에서는 왜 안써놨을까 코드로 보여주면 편할텐데...)

## 컴포지션
>위의 방식의 문제를 해결하기 위해 기존 클래스를 확장하는 대신 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조하게 하는것.
기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 컴포지션 이라 한다.

### EX)

~~~java
public class InstrumentedSet<E> extends ForwardingSet<E>{
	private int addCount = 0;
    
    public InstrumentedSet(Set<E> s){
    	super(s);
    }
    
    @Override public boolean add(E e){
    	addCount++;
        return super.add(e);
    }
    
    @Override public boolean addAll(Collection<? extends E> c){
    	addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount(){
    	return addCount;
    }
}
~~~

~~~java
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    public void clear() {
        s.clear();
    }

    public boolean contains(Object o) {
        return s.contains(o);
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public int size() {
        return s.size();
    }

    public Iterator<E> iterator() {
        return s.iterator();
    }

    public boolean add(E e) {
        return s.add(e);
    }

    public boolean remove(Object o) {
        return s.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    public Object[] toArray() {
        return s.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean equals(Object o) {
        return s.equals(o);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public String toString() {
        return s.toString();
    }
}
~~~

>- InstrumentedSet 은 HashSet 의 모든 기능을 정의한 Set 인터페이스를 활용해 설계되어 견고하고 아주 유연하다.
- 구체적으로 Set 인터페이스를 구현했고 Set 의 인스턴스를 인수로 받는 생성자를 하나 제공한다.
- Set 의 계측 기능을 덧씌워 새로운 Set 으로 만드는것이 이 클래스의 핵심.