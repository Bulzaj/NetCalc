package netcalc.core;

public class Octet {

    private int id;
    private int value;
    private String binaryValue;

    public Octet(int id, int value) {
        this.id = id;
        this.value = value;
        binaryValue =  Integer.toBinaryString(this.value);
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getBinaryValue() {
        return binaryValue;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
