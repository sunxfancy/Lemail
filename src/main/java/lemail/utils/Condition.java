package lemail.utils;

/**
 * Created by XYN on 7/4/2015.
 */
public class Condition {
    private String condition;
    private String name;
    private Object value;

    public Condition(String name, String condition, Object value) {
        this.condition = condition;
        this.value = value;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
