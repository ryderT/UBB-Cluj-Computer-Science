package Model;

public class MyException extends Exception {
    public MyException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
