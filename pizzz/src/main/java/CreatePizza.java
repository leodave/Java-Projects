import enums.Crust;
import enums.Size;
import enums.Toppings;

public class CreatePizza {

    public CreatePizza(Size size, Crust crust) {
        SecretPizzaBase secretPizzaBase = new SecretPizzaBase(size, crust);
        validatePizza(size, crust);
        System.out.println("This pizza has been created! with size: " + secretPizzaBase.getSize() +
                "and crust: " + secretPizzaBase.getCrust());
    }

    public void addToppings(enums.Toppings... toppings) {
        for (Toppings topping : toppings) {
            System.out.println("- " + topping);
        }
        System.out.println("toppings added are: " + toppings.toString());
    }

    public void validatePizza(Size size, Crust crust, enums.Toppings... toppings) {
        if (size == Size.SMALL && crust == Crust.STUFFED)
            throw new IllegalArgumentException("too small to be stuffed");
        if (size == Size.SMALL && toppings.length > 2)
            throw new IllegalArgumentException("too small to have it all");
        else {
            System.out.println("This pizza is valid.");
        }
    }
}