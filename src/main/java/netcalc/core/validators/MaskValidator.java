package netcalc.core.validators;

import netcalc.core.Mask;

public class MaskValidator {

    public static final int MASK_MIN_VALUE = 0;
    public static final int MASK_MAX_VALUE = 32;

    private Mask mask;

    public MaskValidator(Mask mask) {
        this.mask = mask;
    }

    public void isMaskValueCorrect() throws RuntimeException {
        if (mask.getMaskValue() < MASK_MIN_VALUE ||mask.getMaskValue() > MASK_MAX_VALUE ) {
            throw new RuntimeException("Mask value is not correct");
        }
    }
}
