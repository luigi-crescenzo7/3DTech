package model;

import java.util.List;
import java.util.StringJoiner;

public class SqlJoiner {
    public static String queryJoiner(List<Condition> conditionList, String alias) {
        StringJoiner joiner = new StringJoiner("AND");
        for (Condition condition : conditionList)
            joiner.add(String.format("%s.%s%s", alias, condition.toString(), condition.getValue()));
        return joiner.toString();
    }
}
