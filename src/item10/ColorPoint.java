package item10;


import java.util.Objects;

//public class colorPoint extends Point{
//    private final Color color;
//    public colorPoint(int x, int y,Color color) {
//        super(x, y);
//        this.color = color;
//    }
//}
public class ColorPoint {
    private final Point point;
    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }
}
