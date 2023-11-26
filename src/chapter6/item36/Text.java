package chapter6.item36;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    public void applyStyles(Set<Style> styles) {
        System.out.printf("적용되는 텍스트 스타일은 : %s%n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        Text text = new Text();
        EnumSet<Style> enumSet = EnumSet.of(Style.BOLD, Style.ITALIC);
        // EnumSet 은 가변.
        enumSet.remove(Style.BOLD);
        text.applyStyles(enumSet);

        Set<Style> unmodifiableSet = Collections.unmodifiableSet(EnumSet.of(Style.UNDERLINE, Style.STRIKETHROUGH));

//        unmodifiableSet.add(Style.BOLD);
        // Error
    }

    // EnumSet 클래스는 비트필드 수준의 명료함과 성능을 제공.
    // 단점으로는 불변 EnumSet 을 만들수 없다.
    // Collections.unmodifiableSet 으로 EnumSet 을 감싸 사용하자.
}
