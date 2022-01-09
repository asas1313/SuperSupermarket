package model;

public class StoredProduct {
    private final Product product;
    private Integer quantity;

    public StoredProduct(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    @Override
    public String toString() {
        return product.getName() + " Quantity: " + quantity;
    }
}
