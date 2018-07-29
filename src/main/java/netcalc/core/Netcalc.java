package netcalc.core;

import java.util.ArrayList;
import java.util.List;

public class Netcalc {

    private Ipv4Address baseAddress;
    private Ipv4Address netAddress;
    private Ipv4Address broadcastAddress;
    private int maxHostNumber;
    private List<Octet> baseAddressOctets;
    private List<Octet> maskOctets;
    private List<Octet> netAddressOctets;
    private List<Octet> broadcastAddressOctets;

    public Netcalc(Ipv4Address baseAddress) {
        this.baseAddress = baseAddress;
        baseAddressOctets = baseAddress.getOctets();
        maskOctets = baseAddress.getMask().getOctets();
        calcNetAddress();
        calcBroadcastAddress();
        calcMaxHostNumber();
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

    private void calcMaxHostNumber() {
        maxHostNumber = (int) Math.pow(2, 32-(baseAddress.getMask().getNetPart()*8 + baseAddress.getMask().getHostPart()))-2;
    }
    @Override
    public String toString() {
        return "Net Address: " + netAddress.toString() +  "\n" +
                "Broadcast Address: " + broadcastAddress.toString() + "\n" +
                 "Max host number: " + maxHostNumber + "\n";
    }

    public static void main(String[] args) {
        Octet octet1 = new Octet(1, 192);
        Octet octet2 = new Octet(2, 168);
        Octet octet3 = new Octet(3, 1);
        Octet octet4 = new Octet(4, 145);
        Mask mask = new Mask(26);
        Ipv4Address ipv4Address = new Ipv4Address(octet1, octet2, octet3, octet4, mask);
        Netcalc netcalc = new Netcalc(ipv4Address);
        System.out.println(netcalc.toString());


    }
}
