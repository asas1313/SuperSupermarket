package model;

public class Product {
    private final String name;
    private final Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(name + " (price: %.1f)", price);
    }
}
