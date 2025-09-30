public class Car {

    private String wheel;

    private String motor;

    private String window;

    public Car(Builder builder) {
        this.wheel = builder.wheel;
        this.motor = builder.motor;
        this.window = builder.window;
    }

    public String getWheel() {
        return this.wheel;
    }

    static class Builder {

        private String wheel;

        private String motor;

        private String window;

        public Builder wheel(String wheel) {
            this.wheel = wheel;
            return this;
        }

        public Builder motor(String motor) {
            this.wheel = wheel;
            return this;
        }

        public Builder window(String window) {
            this.wheel = wheel;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

}