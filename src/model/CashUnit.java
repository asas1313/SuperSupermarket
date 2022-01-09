package model;

public class CashUnit {
    private final String name;
    private final float value;

    public CashUnit(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }
}
