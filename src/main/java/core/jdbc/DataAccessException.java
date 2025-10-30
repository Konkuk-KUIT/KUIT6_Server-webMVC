package core.jdbc;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String msg, Throwable cause) {
        cause.printStackTrace();
        //super(msg, cause);
    }
}
