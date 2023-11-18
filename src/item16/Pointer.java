package item16;

public class Pointer {
    private double x;
    private double y;

    private Pointer(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Pointer of(double x, double y) {
        return new Pointer(x,y);
    }
}
