package Conexao;


public class DbException extends RuntimeException {
    public DbException(String message) {
        super(message);
    }

    public DbException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
