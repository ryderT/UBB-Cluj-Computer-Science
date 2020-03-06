package Value;

import Type.Type;
import Type.RefType;

public class RefValue implements Value {

    private int address;
    private Type locationType;
    public RefValue(Type locType,int addr) {
        this.address = addr;
        this.locationType = locType;
    }
    public int getAddress() {return address;}

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "("+Integer.toString(address)+","+locationType.toString()+")";
    }

    @Override
    public Type getType() { return new RefType(locationType);}

    @Override
    public Value deepCopy() {
        return new RefValue(locationType,address);
    }
}
