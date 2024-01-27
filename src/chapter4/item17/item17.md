# 불변클래스
>- 이름 그대로 변경할수 없는 클래스 라는 뜻이다.
- 변경할수 없다 라는것은 클래스 객체들이 저장되는 Heap 영역에서 변경이 불가능 하다는 것이지 재할당이 불가능한것은 아니다. (참조하는 객체의 주소값이 바뀌는건 가능)
- 대표적인 불변클래스 로는 Strig, Boolean , Integer, Float, Long 등이 있다.

## 불변클래스의 장점
>- 생성자 , 접근 메서드에 대한 방어 복사가 필요 없으며 멀티스레드 환경에서 동기화 처리 없이 객체를 공유 할수 있는 Thread safe 하다.
- 불변객체는 자유롭게 공유할수 있고 불변 객체 끼리는 내부 데이터를 공유할수 있다.
- 객체를 만들때 다른 불변 객체들을 구성요소로 사용하면 이점이 많다.
- 불변 객체는 그 자체로 실패 원자성을 제공한다.

## 불변클래스의 단점
>- 객체가 가지는 값마다 새로운 객체가 필요하여 메모리 누수가 발생할수 있고 새로운 객체를 계속 생성해야 하기 때문에 성능저하가 발생할수 있다.

## 불변 클래스를 만들기 위한 규칙
> - 객체의 상태를 변경하는 메서드를 제공하지 않는다.
    EX) setter
- 클래스를 확장할수 없도록 한다.
  EX) 클래스를 final 로 선언하여 하위 클래스에서 객체의 상태를 변하게 할수 있음을 방지한다.
- 설계자의 의도를 불변임을 명시하기 위해 모든 필드를 final 로 선언한다.
- 필드가 참조하는 가변객체를 클라이언트가 접근할수 없게 모든 필드를 private 으로 선언한다.
- 자신 외에는 내부의 가변 컴포넌트에 접근할수 없도록 한다.

## 예시
~~~java
// 클래스를 final 로 선언 , 클래스를 확장할수 없다.
public final class Complex{
// 모든 필드를 private final 로 선언
	private final double re;
    private final double im;
    
    // setter 메서드가 없다.
    public Complex(double re, double im){
    	this.re = re;
        this.im;
    }
    
    public double realPart(){
    	return re;
    }
    
    public double imaginaryPart(){
    	return im;
    }
    
   public Complex plus(Complex c){
   	return new Complex(re + c.re , im + c.im);
   }
   
   public Complex minus(Complex c){
   	return new Complex(re -c.re, im - c.im);
   }
   
   public Complex times(Complex c){
   	return new Complex(re * c.re - im* c.im, re * c.im + im * c.re);
   }
   
   public Complex divideBy(Complex c){
   	double tmp = c.re * c.re + c.im * c.im;
    return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
   }
   
   @Override public boolean euqals(Object o){
   	if(o == this)
    	return true;
   
    if(!(o instanceof Complex))
   		return false;
    Complex c = (Complex) o;
    
    return Double.compare(c.re, re) == 0 && Double.compare(c.im , im) == 0;
  }
  
  @Override public int hashCode(){
  	return 31 * Double.hashCode(re) + Double.hashCode(im);
  }
  
  @Override public String toString(){
  	return "(" + re + " + " + im + "i)";
  }
}
~~~

>- 위 클래스는 실수부와 허수부 필드를 가지는 복소수를 표현하고 있다.
- Object 의 equals , hashCode , toString을 재정의 하였다.
- 각 메서드 들은 필드를 수정하는것이 아니라 새로운 Complex 객체를 반환한다.
- 불변 클래스는 한번 만든 인스턴스를 최대한 재활용 하는것이 좋다.

## 예시개선
~~~java
public class Complex{
	private final double re;
    private final double im;
    
    // 생성자를 private
    private Complex(double re, double im){
    	this.re = re;
        this.im = im;
    }
    
    // 정적 팩터리 메서드
    public static Complex valueOf(double re ,double im){
    	return new Complex(re , im);
    }
    
   
    public static Complex zero(){
    	return new Complex(0,0);
    }
}
~~~


## 결론
>- 모든 클래스를 불변으로 만들수는 없지만 변경할수 있는 부분을 최소화 하는것이 좋다.
- 합당한 이유가 없다면 모든 필드는 private final 로 설정한다.
- 생성자는 불변식 설정이 모두 완료된 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.