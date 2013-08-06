package numbercruncher.primeutils;

/**
 * Interface for a caller of the Miller-Rabin test.
 */
public interface MillerRabinCaller
{
    /**
     * Report on the status of the Miller-Rabin test.
     * @param status the current status
     */
    void reportStatus(MillerRabinStatus status);
}
