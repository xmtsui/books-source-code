package numbercruncher.mathutils;

/**
 * Print text and numbers right-aligned in columns.
 */
public class AlignRight
{
    /** line size */   private int lineSize;

    /**
     * Constructor.
     */
    public AlignRight() {}

    /**
     * Print text right-aligned in the column.
     * @param text the text to print
     * @param width the column width
     */
    public void print(String text, int width)
    {
        int padding = width - text.length();
        while (--padding >= 0) System.out.print(" ");
        System.out.print(text);

        lineSize += width;
    }

    /**
     * Print an integer value right-aligned in the column.
     * @param value the value to print
     * @param width the column width
     */
    public void print(int value, int width)
    {
        print(Integer.toString(value), width);
    }

    /**
     * Print a float value right-aligned in the column.
     * @param value the value to print
     */
    public void print(float value, int width)
    {
        print(Float.toString(value), width);
    }

    /**
     * Print a double value right-aligned in the column.
     * @param value the value to print
     * @param width the column width
     */
    public void print(double value, int width)
    {
        print(Double.toString(value), width);
    }

    /**
     * Print a line.
     */
    public void println()
    {
        System.out.println();
        lineSize = 0;
    }

    /**
     * Print an underline.
     */
    public void underline()
    {
        System.out.println();
        for (int i = 0; i < lineSize; ++i) System.out.print("-");
        System.out.println();
        lineSize = 0;
    }
}