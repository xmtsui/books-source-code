package numbercruncher.program9_1;

import numbercruncher.matrix.RowVector;
import numbercruncher.matrix.SquareMatrix;
import numbercruncher.matrix.MatrixException;

/**
 * Represent a vertex of the wire-frame cube in three dimensions.
 */
class Vertex extends RowVector
{
    /**
     * Constructor.
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    Vertex(float x, float y, float z)
    {
        super(4);

        values[0][0] = x;
        values[0][1] = y;
        values[0][2] = z;
        values[0][3] = 1;
    }

    /**
     * Return this vertex's x value.
     * @return the x value
     */
    float x() { return values[0][0]; }

    /**
     * Return this vertex's y value.
     * @return the y value
     */
    float y() { return values[0][1]; }

    /**
     * Return this vertex's z value.
     * @return the z value
     */
    float z() { return values[0][2]; }

    /**
     * Transform this vector by multiplying it
     * by a transformation matrix.
     * @param t the transformation matrix
     */
    void multiply(SquareMatrix t) throws MatrixException
    {
        RowVector rv = t.multiply(this);
        this.values = rv.values();
    }
}