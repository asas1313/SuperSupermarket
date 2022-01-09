package service;

import exceptions.NotEnoughChangeException;
import exceptions.PayNotAcceptedException;
import exceptions.SoldOutException;
import model.StoredProduct;

/**
 * Service mimics the Supermarket operations and is used in public APIs.
 * <p>
 * Service uses StoredProduct class which contains a reference to Product
 * and an integer quantity of that Product
 */
public interface SupermarketService {
    SupermarketService openSupermarket();

    /**
     * Constructs nicely formatted report about all sellable items in the Supermarket.
     */
    String productInventory();

    /**
     * Constructs nicely formatted report about all bills and coins in the Supermarket's cash register.
     */
    String cashInventory();

    /**
     * Constructs nicely formatted Price List report about sellable items in the Supermarket.
     */
    String getPriceList();

    /**
     * Service finds item by name, presents payable amount, collects the bills and coins, decreases
     * item amount in the Store and arranges the cash register.
     */
    StoredProduct buyItem(String name) throws SoldOutException;

    /**
     * Service collects bills and coins for the amount provided and gives the change if needed
     */
    void payAmount(float amountToBePayed) throws NotEnoughChangeException, PayNotAcceptedException;

    /**
     * Service decreases the actual amount of the Stored product in the Supermarket
     */
    void providePurchase(StoredProduct item) throws SoldOutException;
}
