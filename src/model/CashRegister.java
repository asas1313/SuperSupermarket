package model;

public class CashRegister {
    CashUnitStorage unit2Storage;
    CashUnitStorage unit1Storage;
    CashUnitStorage unit05Storage;
    CashUnitStorage unit01Storage;

    public CashRegister() {
        unit2Storage = new CashUnitStorage(new CashUnit("2", 2f), 0);
        unit1Storage = new CashUnitStorage(new CashUnit("1", 1f), 0);
        unit05Storage = new CashUnitStorage(new CashUnit("0.5", 0.5f), 0);
        unit01Storage = new CashUnitStorage(new CashUnit("0.1", 0.1f), 0);
    }

    public void fillCashRegister() {
        unit2Storage.setAmount(50);
        unit1Storage.setAmount(50);
        unit05Storage.setAmount(50);
        unit01Storage.setAmount(50);
    }

    public CashUnitStorage getUnit2Storage() {
        return unit2Storage;
    }

    public CashUnitStorage getUnit1Storage() {
        return unit1Storage;
    }

    public CashUnitStorage getUnit05Storage() {
        return unit05Storage;
    }

    public CashUnitStorage getUnit01Storage() {
        return unit01Storage;
    }

    @Override
    public String toString() {
        String inventory = "";
        inventory += unit2Storage.toString();
        inventory += '\n' + unit1Storage.toString();
        inventory += '\n' + unit05Storage.toString();
        inventory += '\n' + unit01Storage.toString();
        return inventory;
    }

    public void clone(CashRegister cashRegister) {
        unit2Storage.setAmount(cashRegister.getUnit2Storage().getAmount());
        unit1Storage.setAmount(cashRegister.getUnit1Storage().getAmount());
        unit05Storage.setAmount(cashRegister.getUnit05Storage().getAmount());
        unit01Storage.setAmount(cashRegister.getUnit01Storage().getAmount());
    }
}
