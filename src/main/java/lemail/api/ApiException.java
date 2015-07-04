package lemail.api;

/**
 * 这是一个带有错误码的Exception
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
