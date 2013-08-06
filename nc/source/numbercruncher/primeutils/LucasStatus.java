package numbercruncher.primeutils;

/**
 * The current status of the Lucas test.
 */
public class LucasStatus
{
    /** trial integer a */      int     a;
    /** prime factor */         int     q;
    /** exponent of a */        int     exponent;
    /** modulo value */         int     value;
    /** pass or fail */         boolean pass;

    public int getA()        { return a; }
    public int getQ()        { return q; }
    public int getExponent() { return exponent; }
    public int getValue()    { return value; }
    public boolean didPass() { return pass; }
}
