package ru.shulgindaniil.ast.visitor;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class VisitorData {
    private String value;

    public VisitorData(boolean value) {
        this.value = (value) ? "1" : "0";
    }
    public VisitorData(String value) {
        this.value = value;
    }
    public VisitorData(Double value) {
        this.value = value.toString();
    }

    public boolean toBoolean() {
        try {
            int parsedValue = (int) Double.parseDouble(value);
            return parsedValue != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double toDouble() throws NumberFormatException {
        return Double.parseDouble(value);
    }

    public void setValue(boolean value) {
        this.value = (value) ? "1" : "0";
    }
    public void setValue(double value) {
        this.value = Double.toString(value);
    }
    public void setValue(String value){
        this.value = value;
    }
}
