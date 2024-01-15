# 정적 유틸리티를 잘못 사용한 예

~~~java
public class SpellChecker{
	private static final Lexicon dictionary = ...;
    private SpellChecker(){};
    
    public static boolean isValid(String word){}
    public static List<String> seggestions(String typo){}
}
~~~



# 싱글턴을 잘못 사용한 예
~~~java
public class SpellChecker{
	private final Lexicon dictionary = ...;
    private SpellChecker(){};
    
    public static SpellChecker INSTANCE = new SpellChecker();
    
    public static boolean isValid(String word){}
    public static List<String> seggestions(String typo){}
}
~~~

# 문제점
>- 두방식 모두 사전을단 하나만 사용한다고 가정했는데 사전이 어려개면 어떻게 할것인가?

# 해결
~~~java
public class SpellChecker{
	private final Lexicon dictionary;
    
    public SpellChecker(Lexicon dictionary){
    	this.dictionary = Objects.requiredNonNull(dictionary);
    }
    
    public static boolean isValid(String word){}
    public static List<String> seggestions(String typo){} 
}
~~~

>- 인스턴스를 생성할때 필요로 하는 자원을 넘겨주면 유연한 코드를 만들수 있다.
- 불변을 보장하여 여러 클라이언트가 안심하고 공유할수 있다.
