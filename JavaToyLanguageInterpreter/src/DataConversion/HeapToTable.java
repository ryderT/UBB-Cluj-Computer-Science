package DataConversion;

public class HeapToTable {
    private int address;
    private String value;

    public HeapToTable(int address,String val){
        this.address=address;
        this.value=val;
    }
    public int getAddress() {
        return address;
    }

    public String getValue() {
        return value;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(address)+" "+value.toString();
    }
}
