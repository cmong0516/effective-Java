package chapter4.item21;

public interface Sized {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
