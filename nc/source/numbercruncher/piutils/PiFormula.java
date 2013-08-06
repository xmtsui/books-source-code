package numbercruncher.piutils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for programs that compute pi.
 */
public abstract class PiFormula
{
    private static final DecimalFormat DECIMAL_FORMAT =
                                new DecimalFormat("00");
    private static final SimpleDateFormat TIME_FORMAT =
                                new SimpleDateFormat("HH:mm:ss.SSS");

    protected long startTime;
    protected long markTime;

    /**
     * Print the string containing the digits of pi.
     * @param piString the string containing the digits of pi
     */
    protected void printPi(String piString)
    {
        System.out.print("\npi = " + piString.substring(0, 2));

        int index  = 2;
        int line   = 0;
        int group  = 0;
        int length = piString.length();

        // Loop for each group of 5 digits
        while (index + 5 < length) {
            System.out.print(piString.substring(index, index+5) +
                             " ");
            index += 5;

            // End of line after 10 groups.
            if (++group == 10) {
                System.out.println();

                // Print a blank line after 10 lines.
                if (++line == 10) {
                    System.out.println();
                    line = 0;
                }

                System.out.print("       ");
                group = 0;
            }
        }

        // Print the last partial line.
        if (index < length) {
            System.out.println(piString.substring(index));
        }
    }

    /**
     * Return a timestamp string that contains the elapsed time period.
     * @param time the starting time of the period
     * @return the timestamp string
     */
    protected String timestamp(long time)
    {
        // Current time in hh:mm:ss.
        String tString = TIME_FORMAT.format(new Date());

        long   elapsed = (System.currentTimeMillis() - time + 500)
                            /1000;
        long   hours   = elapsed/(60*60);
        long   minutes = (elapsed%(60*60))/60;
        long   seconds = elapsed%60;

        // Current time followed by elapsed time as (hh:mm:ss).
        return tString + " (" + DECIMAL_FORMAT.format(hours) +
                          ":" + DECIMAL_FORMAT.format(minutes) +
                          ":" + DECIMAL_FORMAT.format(seconds) +
                          ")";
    }
}