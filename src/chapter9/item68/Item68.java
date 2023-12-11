package chapter9.item68;

public class Item68 {
    // 일반적으로 통용되는 명명 규칙을 따르라.


    //1) 패키지
    //
    //패키지와 모듈 이름은 각 요소를 점(.)으로 구분하여 계층적으로 짓는다
    //
    //이때 요소들은 모두 소문자 알파벳 혹은 숫자로 이루어진다
    //조직 바깥에서 사용될 패키지라면 조직의 인터넷 도메인 이름을 역순으로 사용한다(com.google, org.eff)
    //예외적으로 표준 라이브러리와 선택적 패키지들은 java와 javax로 시작한다
    //
    //
    //패키지 이름의 나머지는 해당 패키지를 설명하는 하나 이상의 요소로 이뤄진다
    //
    //각 요소는 일반적으로 8자 이하의 짧은 단어로 하되, 통용되는 약어를 추천한다(utilities -> util)
    //합성어의 경우각 단어의 첫 글자만 따서 써도 좋다(awt)
    //
    //
    //
    //2) 클래스와 인터페이스
    //
    //하나 이상의 단어로 이뤄지며, 각 단어는 대문자로 시작한다
    //
    //여러 단어의 첫 글자만 딴 약자나 널리 통용되는 약어(max, min)을 제외하고는 단어를 줄여 쓰지 않도록 한다
    //약어의 경우 첫 글자를 대문자로만 하는 편이 일반적이다(HttpUrl, Db)
    //
    //
    //
    //3) 메서드와 필드 이름
    //
    //첫 글자를 소문자로 쓴다는 점만 빼면 클래스 명명 규칙과 같다
    //첫 단어가 약자라면 단어 전체가 소문자여야 한다
    //예외적으로 상수 필드를 구성하는 단어는 모두 대문자로 쓰며, 단어 사이를 밑줄로 구분한다
    //
    //상수 필드는 static final 필드의 타입이 기본 타입이나 불변 참조 타입(아이템 17)인 경우를 의미한다
    //
    //
    //지역변수엔 약어를 써도 좋다
    //
    //약어를 써도 그 변수가 사용되는 문맥에서 의미를 쉽게 유추할 수 있기 떄문에(i, denom, houseNum)
    //
    //
    //타입 매개변수의 이름은 보통 한 문자료 표현한다
    //
    //임의의 타입엔 T, 컬렉션 원소의 타입은 E, 맵의 키와 값에는 K와 V, 예외에는 X, 메서드의 반환 타입에는 R
    //그 외의 임의 타입의 시퀀스에는 T, U, V 혹은 T1, T2, T3를 사용한다

    //문법 규칙
    //1) 클래스
    //
    //객체를 생성할 수 있는 클래스(열거 타입 포함)의 이름은 보통 단수 명사나 명사구를 사용한다(Thread, PriorityQueue, ChessPiece)
    //
    //이와 반대로 객체를 생성할 수 없는 클래스(아이템 4)의 이름은 보통 복수형 명사로 짓는다
    //
    //
    //인터페이스 이름은 클래스와 똑같이 짓거나(Collection, Comparator), able 혹은 ible로 끝나는 형용사로 짓는다(Runnable, Iterable, Accessible)
    //
    //애너테이션은 그 활용이 다양한 관계로 지배적인 규칙이 없이 명사, 동사, 전치사, 형용사가 두루 쓰인다
    //
    //
    //
    //2) 메서드
    //
    //어떤 동작을 수행하는 메서드의 이름은 (목적어를 포함한) 동사구로 짓는다(append, drawImage)
    //boolean값을 반환하는 메서드라면 보통 is나 has로 시작하고 명사나 명사구, 혹은 형용사로 기능하는 아무 단어나 구로 끝나도록 짓는다(isDigit, isEmpty, hasSiblings)
    //
    //반환 타입이 boolean이 아니거나 해당 인스턴스의 속성을 반환하는 메서드의 이름은 보통 명사, 명사구, 혹은 get으로 시작하는 동사구로 짓는다(size, hashCode, getTime)
    //
    //
    //객체 타입을 바꿔서 다른 타입의 또 다른 객체를 반환하는 인스턴스 메서드의 이름은 보통 toType 형태로 짓는다(toString, toArray)
    //객체의 내용을 다른 뷰로 보여주는 메서드(아이템 6)의 이름은 asType 형태로 짓는다(asList)
    //정적 팩터리의 이름은 다양하지만 from, of, valueOf, instance, getInstance, newInstance, getType, newType(아이템 1)을 흔히 사용한다
}