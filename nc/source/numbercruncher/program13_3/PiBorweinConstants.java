package numbercruncher.program13_3;

/**
 * Constants for the Borwein pi algorithm.
 */
interface PiBorweinConstants
{
    static final int INITIALIZING = -1;
    static final int INVERTING    = -2;
    static final int DONE         = -3;
    static final int STOPPED      = -4;

    static final String STOPPED_EXCEPTION = "STOPPED";
}
