import enums.Crust;
import enums.Size;

public class SecretPizzaBase {

    Size size;

    Crust crust;

    public SecretPizzaBase(Size size, Crust crust) {
        this.size = size;
        this.crust = crust;
        System.out.println("This is a Pizza made by our secret sauce!");
    }

    public Size getSize() {
        System.out.println("size is : " + size);
        return size;
    }

    public Crust getCrust() {
        System.out.println("crust is : " + crust);
        return crust;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }
}