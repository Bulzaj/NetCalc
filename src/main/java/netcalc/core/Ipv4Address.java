package netcalc.core;

import netcalc.core.validators.MaskValidator;
import netcalc.core.validators.OctetValidator;

import java.util.ArrayList;
import java.util.List;

public class Ipv4Address {

    private Octet octet1, octet2, octet3, octet4;
    private List<Octet> octets;
    private Mask mask;
    private OctetValidator octetValidator1;
    private OctetValidator octetValidator2;
    private OctetValidator octetValidator3;
    private OctetValidator octetValidator4;
    private MaskValidator maskValidator;

    public Ipv4Address(Octet octet1, Octet octet2, Octet octet3, Octet octet4, Mask mask) {
        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
        this.mask = mask;
        octets = new ArrayList<>();
        octetValidator1 = new OctetValidator(octet1);
        octetValidator2 = new OctetValidator(octet2);
        octetValidator3 = new OctetValidator(octet3);
        octetValidator4 = new OctetValidator(octet4);
        maskValidator = new MaskValidator(mask);

        try {
            octetValidator1.isOctetValueCorrect();
            octetValidator2.isOctetValueCorrect();
            octetValidator3.isOctetValueCorrect();
            octetValidator4.isOctetValueCorrect();
            maskValidator.isMaskValueCorrect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        octets.add(this.octet1);
        octets.add(this.octet2);
        octets.add(this.octet3);
        octets.add(this.octet4);
    }

    public Octet getOctet1() {
        return octet1;
    }

    public Octet getOctet2() {
        return octet2;
    }

    public Octet getOctet3() {
        return octet3;
    }

    public Octet getOctet4() {
        return octet4;
    }

    public List<Octet> getOctets() {
        return octets;
    }

    public Mask getMask() {
        return mask;
    }

    @Override
    public String toString() {
        String result = "";
        for (Octet octet : octets) {
            result += octet.getValue() + ".";
        }
        result += "/"+mask.getMaskValue();
        return result;
    }
}
