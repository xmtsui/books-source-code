package numbercruncher.mathutils;

/**
 * Constants related to the IEEE 754 standard.
 */
public interface IEEE754Constants
{
    static final int FLOAT_SIGN_INDEX         =    0;
    static final int FLOAT_SIGN_SIZE          =    1;
    static final int FLOAT_EXPONENT_INDEX     =    1;
    static final int FLOAT_EXPONENT_SIZE      =    8;
    static final int FLOAT_EXPONENT_RESERVED  =  255;
    static final int FLOAT_EXPONENT_BIAS      =  127;
    static final int FLOAT_FRACTION_INDEX     =    9;
    static final int FLOAT_FRACTION_SIZE      =   23;

    static final int DOUBLE_SIGN_INDEX        =    0;
    static final int DOUBLE_SIGN_SIZE         =    1;
    static final int DOUBLE_EXPONENT_INDEX    =    1;
    static final int DOUBLE_EXPONENT_SIZE     =   11;
    static final int DOUBLE_EXPONENT_RESERVED = 2047;
    static final int DOUBLE_EXPONENT_BIAS     = 1023;
    static final int DOUBLE_FRACTION_INDEX    =   12;
    static final int DOUBLE_FRACTION_SIZE     =   52;
}