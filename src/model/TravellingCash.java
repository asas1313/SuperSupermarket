package model;

import java.util.ArrayList;
import java.util.List;

public class TravellingCash {
    List<CashUnitStorage> cashUnitStorages = new ArrayList<>();

    public void addChangeUnit(float unit) {
        boolean addUnit = true;
        for (CashUnitStorage cashUnitStorage : cashUnitStorages) {
            if (cashUnitStorage.getCashUnit().getValue() == unit) {
                cashUnitStorage.increaseUnit();
                addUnit = false;
                break;
            }
        }
        if (addUnit) {
            cashUnitStorages.add(new CashUnitStorage(new CashUnit(String.valueOf(unit), unit), 1));
        }

    }

    public void printChange() {
        for (CashUnitStorage cashUnitStorage : cashUnitStorages) {
            System.out.printf("Value: %s, quantity: %d\n"
                    , cashUnitStorage.getCashUnit().getName()
                    , cashUnitStorage.getAmount());
        }
    }
}
