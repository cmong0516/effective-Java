package chapter6.item39;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Test39 어노테이션의 어노테이션 : 메타어노테이션
@Retention(RetentionPolicy.RUNTIME)
// 런타임에도 유지되어야 한다.
@Target(ElementType.METHOD)
// 메서드 선언에만 사용.
public @interface Test39 {
}
