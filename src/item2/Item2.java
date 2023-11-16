package item2;

import item2.NyPizza.Size;
import item2.Pizza.Topping;

public class Item2 {
    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(Size.SMALL).addTopping(Topping.SAUSAGE).addTopping(Topping.ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(Topping.HAM).sauceInside().build();

        System.out.println(nyPizza.toString());
        System.out.println(calzone.toString());
    }
}