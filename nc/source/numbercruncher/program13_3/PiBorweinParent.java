package numbercruncher.program13_3;

/**
 * Interface for the parent of a PiBorwein object.
 */
interface PiBorweinParent
{
    /**
     * Scale notification.
     * @param scale the scale being used
     */
    void notifyScale(int scale);

    /**
     * Phase notification.
     * @param phase the current phase
     */
    void notifyPhase(int phase);

    /**
     * Task notification.
     * @param task the current computation task
     */
    void notifyTask(String task);
}