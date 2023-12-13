package chapter12.item87;

import java.io.Serializable;

public class Name implements Serializable {
    private final String lastName;
    private final String firstName;
    private final String middleName;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    // 기본 직렬화 형태가 적합하다고 결정했더라도 북변식 보장과 보안을 위해 readObject 메서드를 제공하여 lastName ,firtstName 필드가 null 이 아님을 보장해야 한다.


}
