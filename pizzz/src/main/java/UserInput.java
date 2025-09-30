public class UserInput {

    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Pizza Creator!");

        // Get pizza size
        System.out.println("Choose pizza size (SMALL, MEDIUM, LARGE):");
        String sizeInput = scanner.nextLine().toUpperCase();
        Size size = Size.valueOf(sizeInput);

        // Get pizza crust
        System.out.println("Choose pizza crust (THIN, THICK, STUFFED):");
        String crustInput = scanner.nextLine().toUpperCase();
        Crust crust = Crust.valueOf(crustInput);

        System.out.println("Choosing toppings...");
        System.out.println("Toppings available are: Olives, Feta, Pineapple, Capers, Anchovies");
        Set<Toppings> toppings = new HashSet<>();
        toppings.add(Toppings.valueOf(scanner.nextLine()));

        // Create pizza
        CreatePizza pizza = new CreatePizza(size, crust);
        pizza.addToppings(toppings.toArray(new Toppings[0]));

        User user = new User.Builder().name("John").age(25).email("john@gmail.com").build();
        System.out.println("User is: " + user.getName() + " " + user.getAge() + " " + user.getEmail());
*/
        Car car = new Car.Builder()
                .wheel("Four")
                .motor("BMW")
                .window("dark")
                .build();
        System.out.println(car.getWheel());
    }

}
