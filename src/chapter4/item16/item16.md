# 잘못된 클래스
~~~java
class Point{
	public double x;
    public double y;
}
~~~
>- 필드가 public 으로 설정된 이 클래스는 데이터 필드에 직접 접근할수 있어서 캡슐화의 이점을 제공하지 못한다.
- API 를 수정하지 않고는 내부 표현을 바꿀수 없다.
- 불변성을 보장할수 없다.
- 외부에서 필드에 접근할때 어떤 동작을 수행할수 없다.
- 필드를 모두 private 으로 설정하고 값을 사용할때는 public 접근자 getter 를 사용하자.

# 개선 클래스
~~~java
class Point{
	private double x;
    private double y;
    
    public Point(double x, double y){
    	this.x = x;
        this.y = y;
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    
    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
}
~~~

# 불변 필드를 노출한 public 클래스
~~~java
public final class Time{
	private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    
    public final int hour;
    public final int minute;
    
    public Time(int hour , int minute){
    	if(hour < 0 || hour >= HOURS_PER_DAY)
        	throw new IllegalArgumentException();
        if(minute < 0 || minute >= MINUTES_PER_HOUR)
        	thorw new IllegalArgumentException();
        
       
       this.hour = hour;
       this.minute = minute;
    }
}
~~~

>- public 클래스의 필드가 불변이라고 할지라도 직접 노출할때의 단점이 줄어들뿐 좋은 방법이 아니다.
- API 를 변경하지 않고는 표현 방식을 바꿀수 없고 필드를 읽을때 부수작업을 수행할수 없다.
- 다른점은 불변식을 보장할수 있다.