package item19;

import java.time.Instant;

public final class Sub extends Super{
    private final Instant instant;

    public Sub() {
        // 원래 존재하지만 생략되어 있는 super();
        super();
        this.instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }
}
