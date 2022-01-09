package service;

import exceptions.NotEnoughChangeException;
import exceptions.PayNotAcceptedException;
import exceptions.SoldOutException;
import model.*;

import java.util.NoSuchElementException;

public class SupermarketServiceImpl implements SupermarketService {

    private static final SupermarketServiceImpl instance = new SupermarketServiceImpl();

    private final ProductStorage productStorage;
    private final CashRegister cashRegister;
    private CashRegister paymentCashRegister;
    private TravellingCash changeCollected;

    private SupermarketServiceImpl() {
        productStorage = new ProductStorage();
        cashRegister = new CashRegister();
    }

    public static SupermarketServiceImpl getInstance() {
        return instance;
    }

    public SupermarketService openSupermarket() {
        productStorage.addStoredProduct(
                new StoredProduct(new Product("SODA", 2.3f), 10));
        productStorage.addStoredProduct(
                new StoredProduct(new Product("BREAD", 1.1f), 10));
        productStorage.addStoredProduct(
                new StoredProduct(new Product("WINE", 2.7f), 10));

        cashRegister.fillCashRegister();
        return this;
    }

    @Override
    public String productInventory() {
        return productStorage.toString();
    }

    @Override
    public String cashInventory() {
        return cashRegister.toString();
    }

    @Override
    public String getPriceList() {
        return productStorage.getPriceList();
    }

    @Override
    public StoredProduct buyItem(String item) throws SoldOutException {
        StoredProduct storedProduct;
        try {
            storedProduct = productStorage.findItemByName(item);
        } catch (NoSuchElementException e) {
            throw new SoldOutException("The product by name '" + item + "' does not exist.");
        }

        if (storedProduct.getQuantity() < 1) {
            throw new SoldOutException("The product by name '" + item + "' is already sold out.");
        }

        float price = storedProduct.getProduct().getPrice();
        System.out.printf(
                "You are trying to buy '%s'. You need to pay %.1f\n"
                , item
                , price);

        return storedProduct;
    }

    @Override
    public void payAmount(float amountToBePayed)
            throws NotEnoughChangeException, PayNotAcceptedException {

        float amountPayedTotal = 0.0f;
        float stillNeedsToBePayed = amountToBePayed;

        initializePayment();

        while (amountPayedTotal != amountToBePayed) {
            System.out.println("Provide bill or coin (accepted values: 0.1, 0.5, 1, 2)");
            float amountPayed = InputReader.readFloat();
            _putOneUnit(amountPayed);
            amountPayedTotal += amountPayed;

            if (amountPayed < stillNeedsToBePayed) {
                stillNeedsToBePayed -= amountPayed;
                System.out.printf(
                        "You payed %.1f in total. You still need to pay %.1f\n"
                        , amountPayedTotal
                        , stillNeedsToBePayed);
            } else if (amountPayed > stillNeedsToBePayed) {
                float change = amountPayed - stillNeedsToBePayed;
                stillNeedsToBePayed = 0;

                System.out.printf(
                        "You payed %.1f in total. Your change will be %.1f\n"
                        , amountPayedTotal
                        , change);
                _giveChange(change);

                amountPayedTotal -= change;
            } else {
                break;
            }
        }

        finalizePayment();
    }

    private void _giveChange(float change) {

        double changeLeftToGive = change;
        while (changeLeftToGive >= 2) {
            if (paymentCashRegister.getUnit2Storage().decreaseUnit()) break;
            changeLeftToGive -= 2;
            changeCollected.addChangeUnit(2);
        }

        while (changeLeftToGive >= 1) {
            if (paymentCashRegister.getUnit1Storage().decreaseUnit()) break;
            changeLeftToGive -= 1;
            changeCollected.addChangeUnit(1);
        }

        while (changeLeftToGive >= 0.5) {
            if (paymentCashRegister.getUnit05Storage().decreaseUnit()) break;
            changeLeftToGive -= 0.5;
            changeCollected.addChangeUnit(0.5f);
        }

        while (changeLeftToGive >= 0.1) {
            if (paymentCashRegister.getUnit01Storage().decreaseUnit()) break;
            changeLeftToGive -= 0.1;
            changeCollected.addChangeUnit(0.1f);
        }

        //noinspection IntegerDivisionInFloatingPointContext
        changeLeftToGive = Math.round(changeLeftToGive * 10) / 10;
        if (changeLeftToGive > 0) {
            throw new NotEnoughChangeException();
        }

        changeCollected.printChange();
    }

    @Override
    public void providePurchase(StoredProduct item) throws SoldOutException {
        item.decreaseQuantity();
    }

    public void initializePayment() {
        paymentCashRegister = new CashRegister();
        paymentCashRegister.clone(cashRegister);
        changeCollected = new TravellingCash();
    }

    public void finalizePayment() {
        cashRegister.clone(paymentCashRegister);

    }

    private void _putOneUnit(float unit) {
        switch ((int) (unit * 10)) {
            case 20:
                paymentCashRegister.getUnit2Storage().increaseUnit();
                break;
            case 10:
                paymentCashRegister.getUnit1Storage().increaseUnit();
                break;
            case 5:
                paymentCashRegister.getUnit05Storage().increaseUnit();
                break;
            case 1:
                paymentCashRegister.getUnit01Storage().increaseUnit();
                break;
            default:
                throw new PayNotAcceptedException();
        }
    }
}
