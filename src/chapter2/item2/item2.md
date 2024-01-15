# 매개변수가 많을때 해결책

## 1. 점층적 생성자 패턴 생성
>- 정적 팩터리와 생성자 방식 모두 선택적 매개변수가 많을때 적절히 대응하기가 힘들다.
   선택적 매개변수에 대응하려면 각 선택적 매개변수마다의 생성자나 정적 팩터리 메서드를 생성해주어야하는데 매개변수가 늘어날수록 처리해야할 작업의 양이 매우 많아진다.
   또 이런 방식은 매개변수가 많아질수록 의도하지 않게 동작할수도 있다.

## 2. 자바빈즈 패턴
>- 자바빈즈 패턴은 매개변수가 없는 생성자로 객체를 만든후 세터 메서드들을 호출하여 원하는 매개변수의 값을 설정하는 방법이다.
- 자바빈즈 패턴의 단점은 객체 하나를 만들려면 메서드를 여러개 호출해야 하고 객체가 완전히 생성되기 전까지 일관성이 무너진 상태에 놓이게 된다.
- 일관성이 무너진 상태에 놓이기 때문에 불변객체를 만들수 없으며 스레드 안전성을 얻으려면 추가작업을 해주어야 한다.

## 3. 빌더 패턴
>- 필수 매개변수만으로 생성자를 호출해 빌더 객체를 얻고 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수를 설정한다.
   (점층적 생성자 패턴 + 자바빈즈 패턴 같은 느낌이다.)
   그후 매개변수가 없는 build() 를 호출해 필요한 (보통은 불변인) 객체를 얻는다.
- 빌더는 생성할 클래스 안에 정적 멤버 클래스로 만들어두는것이 일반적이다.

### 일반 클래스 Desk
~~~java
	public class Desk {
	    private final int width;
    	private final int height;
	    private final int wide;
	    private final boolean wheel;
    	private final boolean motion;

	    public static class Builder {
    	    // 필수 매개변수
        	private final int width;
	        private final int height;
    	    private final int wide;
        	// 선택 매개변수
	        // 기본값으로 초기화한다.
    	    private boolean wheel = false;
        	private boolean motion = false;

	        public Builder(int width ,int height, int wide) {
    	        this.width = width;
        	    this.height = height;
            	this.wide = wide;
	        }

    	    public Builder wheel(boolean wheel) {
        	    wheel = wheel;
            	return this;
	        }

    	    public Builder motion(boolean motion) {
        	    motion = motion;
            	return this;
	        }
    	    public Desk build() {
        	    return new Desk(this);
	        }
	    }
    
    

	    private Desk(Builder builder) {
    	    width = builder.width;
        	height = builder.height;
	        wide = builder.wide;
	    }

      public static void main(String[] args) {
          Desk myDesk = new Builder(1400, 700, 700).build();
          Desk wheelDesk = new Builder(1400, 700, 700).wheel(true).build();
          Desk motionDesk = new Builder(1400, 700, 700).motion(true).build();
          Desk wheelAndMotionDesk = new Builder(1400, 700, 700).wheel(true).motion(true).build();
      }
  }

~~~

>- MotionDesk , WheelDesk 를 추상클래스 Desk 를 상속하는 하위 클래스로 만들어서 적용시킬수도 있다.

### 추상클래스 Desk
~~~java
  public abstract class Desk {
      public enum Function{WHEEL,MOTION,GLASS,DRAWER};
      final Set<Function> functions;

      abstract static class Builder<T extends Builder<T>> {
          EnumSet<Function> functions = EnumSet.noneOf(Function.class);

          public T addFunction(Function function) {
              functions.add(Objects.requireNonNull(function));
              return self();
          }

          abstract Desk build();

          protected abstract T self();
      }

      Desk(Builder<?> builder) {
          functions = builder.functions.clone();
      }
  }
~~~

### Desk 를 상속하는 클래스 ADesk

~~~java
  public class ADesk extends Desk {
  
      private final boolean wheel;
      
      public static class Builder extends Desk.Builder<Builder>{
          private boolean wheel = false;

          public Builder wheel(){
              wheel = true;
              return this;
          }

          @Override
          Desk build() {
              return new ADesk(this);
          }

          @Override
          protected Builder self() {
              return this;
          }
      }

      private ADesk(Builder builder) {
          super(builder);
          wheel = builder.wheel;
      }
      
      public static void main(String[] args) {
        ADesk aDesk = new ADesk.Builder().wheel().build();
   	  }
  }

~~~

>- 하위 클래스의 빌더가 정의한 build 메서드는 구체 하위 클래스를 반환하도록 선언하여 ADesk.Builder() 를 사용하면 ADesk 인스턴스를 얻을수 있다.
- 생성자로는 누릴수 없는 가변인수 매개변수를 여러개 사용할수 있다.
- 하지만 빌더 패턴을 사용하려면 빌더를 만들어야 하고 빌더 생성 비용이 크지 않지만 성능에 민감한 상황에서는 문제가 될수도 있다.
- API 는 시간이 지날수록 매개변수가 많아질 확률이 높으나 상황에 맞게 미리 빌더 패턴으로 만들어 두는것도 좋은 방법이다.
