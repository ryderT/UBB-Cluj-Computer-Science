package Exception;

public class MyException extends RuntimeException{
    private String ex;
    public MyException(String exception){this.ex=exception;}
    public String toString(){return this.ex;}
}
