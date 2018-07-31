package netcalc.core;

import java.util.ArrayList;
import java.util.List;

public class Netcalc {

    private Ipv4Address baseAddress;
    private Ipv4Address netAddress;
    private Ipv4Address broadcastAddress;
    private Ipv4Address firstHostAddress;
    private Ipv4Address lastHostAddress;
    private int maxHostNumber;
    private int subnetsNumber;
    private List<Octet> baseAddressOctets;
    private List<Octet> maskOctets;
    private List<Octet> netAddressOctets;
    private List<Octet> broadcastAddressOctets;
    private char netClass;
    private boolean isPrivateNet;

    public Netcalc(Ipv4Address baseAddress) {
        this.baseAddress = baseAddress;
        baseAddressOctets = baseAddress.getOctets();
        maskOctets = baseAddress.getMask().getOctets();
        calcNetAddress();
        calcBroadcastAddress();
        calcMaxHostNumber();
        calcNetClass();
        checkIsPrivateNet();
        calcSubnetsNumber();
        calcFirstHostNumber();
        calcLastHostAddress();
    }

    private void calcNetAddress() {

        netAddressOctets = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            netAddressOctets.add(new Octet(i + 1, baseAddressOctets.get(i).getValue() & maskOctets.get(i).getValue()));
        }

        netAddress = new Ipv4Address(netAddressOctets.get(0),
                netAddressOctets.get(1),
                netAddressOctets.get(2),
                netAddressOctets.get(3),
                baseAddress.getMask());
    }

    private void calcBroadcastAddress() {
        broadcastAddressOctets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int tmpValue = 256 + ~maskOctets.get(i).getValue();
            broadcastAddressOctets.add(new Octet(i+1, tmpValue + netAddressOctets.get(i).getValue()));
        }
        broadcastAddress = new Ipv4Address(broadcastAddressOctets.get(0),
                broadcastAddressOctets.get(1),
                broadcastAddressOctets.get(2),
                broadcastAddressOctets.get(3),
                baseAddress.getMask());
    }

    private void calcFirstHostNumber() {
        int tmp = getNetAddress().getOctet4().getValue()+1;
        firstHostAddress = new Ipv4Address(netAddress.getOctet1(),
                netAddress.getOctet2(),
                netAddress.getOctet3(),
                new Octet(4, tmp),
                baseAddress.getMask());
    }

    private void calcLastHostAddress() {
        int tmp = broadcastAddress.getOctet4().getValue()-1;
        lastHostAddress = new Ipv4Address(broadcastAddress.getOctet1(),
                broadcastAddress.getOctet2(),
                broadcastAddress.getOctet3(),
                new Octet(4, tmp),
                baseAddress.getMask());
    }

    private void calcMaxHostNumber() {
        maxHostNumber = (int) Math.pow(2, 32-(baseAddress.getMask().getNetPart()*8 + baseAddress.getMask().getHostPart()))-2;
    }

    private void calcNetClass() {
        int octet1Value = baseAddress.getOctet1().getValue();
        if (octet1Value >= 1 && octet1Value <=127) {
            netClass = 'A';
        } else if (octet1Value >= 128 && octet1Value <=191) {
            netClass = 'B';
        } else if (octet1Value >= 192 && octet1Value <=223) {
            netClass = 'C';
        } else if (octet1Value >= 224 && octet1Value <=239) {
            netClass = 'D';
        } else if (octet1Value >= 240 && octet1Value <=247) {
            netClass = 'E';
        } else if (octet1Value >= 248 && octet1Value <=255) {
            netClass = 'F';
        }
    }

    private void calcSubnetsNumber() {
        subnetsNumber = (int) Math.pow(2, baseAddress.getMask().hostPart);
    }

    private void checkIsPrivateNet() {
        int octet1Value = baseAddress.getOctet1().getValue();
        int octet2Value = baseAddress.getOctet2().getValue();
        if (octet1Value == 10) {
            isPrivateNet = true;
        } else if (octet1Value == 172 && (octet2Value >= 16 || octet2Value <= 31)) {
            isPrivateNet = true;
        } else if (octet1Value == 192 && octet2Value == 168) {
            isPrivateNet = true;
        } else {
            isPrivateNet = false;
        }
    }

    public Ipv4Address getBaseAddress() {
        return baseAddress;
    }

    public Ipv4Address getNetAddress() {
        return netAddress;
    }

    public Ipv4Address getBroadcastAddress() {
        return broadcastAddress;
    }

    public int getMaxHostNumber() {
        return maxHostNumber;
    }

    public int getSubnetsNumber() {
        return subnetsNumber;
    }

    public char getNetClass() {
        return netClass;
    }

    public boolean isPrivateNet() {
        return isPrivateNet;
    }

    public Ipv4Address getFirstHostAddress() {
        return firstHostAddress;
    }

    public Ipv4Address getLastHostAddress() {
        return lastHostAddress;
    }
}
