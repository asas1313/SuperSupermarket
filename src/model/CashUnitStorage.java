package model;

public class CashUnitStorage {
    private final CashUnit cashUnit;
    private int amount;

    public CashUnitStorage(CashUnit cashUnit, int amount) {
        this.cashUnit = cashUnit;
        this.amount = amount;
    }

    public CashUnit getCashUnit() {
        return cashUnit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean decreaseUnit() {
        if (amount == 0) {
            return true;
        }
        amount--;
        return false;
    }

    @SuppressWarnings("unused")
    public void increaseUnit() {
        amount++;
    }

    @Override
    public String toString() {
        return "Value: " + cashUnit.getName() + ", quantity: " + amount;
    }
}
