package DataConversion;

public class SymTblToTable {
    private String varName;
    private String value;
    public SymTblToTable(String name, String val) {
        this.varName=name;
        this.value=val;
    }

    public String getValue() {
        return value;
    }

    public String getVarName() {
        return varName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return varName+"-->"+value;
    }
}
