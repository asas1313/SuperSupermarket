package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductStorage {
    private final List<StoredProduct> storedProducts;

    public ProductStorage() {
        storedProducts = new ArrayList<>();
    }

    public void addStoredProduct(StoredProduct storedProduct) {
        storedProducts.add(storedProduct);
    }

    @Override
    public String toString() {
        StringBuilder inventory = new StringBuilder();
        for (StoredProduct storedProduct : storedProducts) {
            if (inventory.length() > 0) {
                inventory.append('\n');
            }
            inventory.append(storedProduct.toString());
        }
        return inventory.toString();
    }

    public String getPriceList() {
        StringBuilder priceList = new StringBuilder();
        for (StoredProduct storedProduct : storedProducts) {
            priceList.append(storedProduct.getProduct().toString());
        }
        return priceList.toString();
    }

    public StoredProduct findItemByName(String name) throws NoSuchElementException {
        int index = _getStoredProductIndexByName(name);
        if (index == -1) return null;
        return storedProducts.get(index);
    }

    private int _getStoredProductIndexByName(String storedProductName) {
        for (int i = 0; i < storedProducts.size(); i++) {
            if (storedProducts.get(i).getProduct().getName().equals(storedProductName)) return i;
        }
        return -1;
    }
}
