package chapter6.item34;

public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("185");
        double mass = earthWeight / Planet.EARTH.getSurfaceGravity();

        for (Planet p : Planet.values()) {
            System.out.printf("%s에서의 무게는 %f이다.%n",p,p.surfaceWeight(mass));
        }
    }
}
