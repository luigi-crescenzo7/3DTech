package model;

public enum Operator {
    GREATER_THAN,
    LESS_THAN,
    EQUAL_TO,
    NOT_EQUAL,
    GREATER_EQUAL,
    LESS_EQUAL,
    LIKE;

    /*public String toString() {
        return switch (this) {
            case GREATER_EQUAL -> ">=";
            case LESS_THAN -> "<";
            case GREATER_THAN -> ">";
            case EQUAL_TO -> "=";
            case NOT_EQUAL -> "!=";
            case LESS_EQUAL -> "<=";
            case LIKE -> "LIKE";
        };*/
}
