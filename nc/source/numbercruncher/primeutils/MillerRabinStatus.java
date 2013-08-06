package numbercruncher.primeutils;

/**
 * The current status of the Miller-Rabin test.
 */
public class MillerRabinStatus
{
    // Status codes
    public static final int DONT_KNOW_YET        = 0;
    public static final int DEFINITELY_COMPOSITE = 1;
    public static final int PROBABLY_PRIME       = 2;

    /** random base */          int b;
    /** shifted p-1 */          int k;
    /** no. of right shifts */  int s;
    /** counter */              int i;
    /** modulo value */         int r;
    /** status code */          int code;

    public int getB()     { return b; }
    public int getK()     { return k; }
    public int getS()     { return s; }
    public int getIndex() { return i; }
    public int getValue() { return r; }
    public int getCode()  { return code; }
}
