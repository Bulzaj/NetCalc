package netcalc.core;

import java.util.ArrayList;
import java.util.List;

public class Mask {

    private int maskValue;
    int netPart;
    int hostPart;
    private String binaryValue;
    private List<Octet> octets;

    private void initializeOctets() {

        for (int i=0; i<netPart; i++) {
            octets.add(new Octet(i+1, 255));
        }
        if (netPart < 4) {
            octets.add(new Octet(netPart+1, 256 - (int) Math.pow(2, 8-hostPart)));
        }
        if (octets.size() < 4) {
            for (int i=octets.size(); i<4; i++) {
                octets.add(new Octet(i+1, 0));
            }
        }
    }

    public Mask(int maskValue) {
        this.maskValue = maskValue;
        netPart = maskValue / 8;
        hostPart = maskValue % 8;
        octets = new ArrayList<>();
        initializeOctets();
        for (Octet octet:octets) {
            binaryValue +=Integer.toBinaryString(octet.getValue());
        }
    }

    public int getMaskValue() {
        return maskValue;
    }

    public int getNetPart() {
        return netPart;
    }

    public int getHostPart() {
        return hostPart;
    }

    public List<Octet> getOctets() {
        return octets;
    }

    public String getBinaryValue() {
        return binaryValue;
    }
}
