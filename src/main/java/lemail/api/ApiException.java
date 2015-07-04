package lemail.api;

/**
 * Created by sxf on 15-7-4.
 */
public class ApiException extends Exception{
    private int id;

    public ApiException(int id, String message) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
