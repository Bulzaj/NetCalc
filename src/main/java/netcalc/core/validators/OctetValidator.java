package netcalc.core.validators;

import netcalc.core.Octet;

public class OctetValidator {

    private final static int OCTETS_MIN_VALUE = 0;
    private final static int OCTETS_MAX_VALUE = 255;


    private Octet octet;

    public OctetValidator(Octet octet) {
        this.octet = octet;
    }

    public void isOctetValueCorrect() throws RuntimeException {
        if (octet.getValue() < OCTETS_MIN_VALUE || octet.getValue() > OCTETS_MAX_VALUE) {
            throw new RuntimeException("octet no. " + octet.getId() + " has wrong value");
        }
    }
}
