package model;

public class Condition {

    private final String name;
    private final Operator operator;
    private final Object value;

    public Condition(String name, Operator operator, Object value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return name + operator;
    }
}
