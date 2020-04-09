package domain;

public class Password {
    private Integer length;
    private String content;
    public Password(String password){
        this.content=password;
        this.length=password.length();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content+'\n';
    }
}
