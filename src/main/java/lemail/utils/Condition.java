package lemail.utils;

/**
 * Created by XYN on 7/4/2015.
 */
public class Condition {
    private String condition;
    private Object value;

    public Condition(String condition, Object value) {
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
