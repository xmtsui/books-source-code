package numbercruncher.program9_1;

import numbercruncher.matrix.SquareMatrix;
import numbercruncher.matrix.IdentityMatrix;
import numbercruncher.matrix.MatrixException;

/**
 * Transformations of a graphic image.
 */
class Transformation
{
    /** translation matrix */
    private SquareMatrix translate = new IdentityMatrix(4);

    /** scaling matrix */
    private SquareMatrix scale = new IdentityMatrix(4);

    /** matrix to rotate about the x axis */
    private SquareMatrix rotateX = new IdentityMatrix(4);

    /** matrix to rotate about the y axis */
    private SquareMatrix rotateY = new IdentityMatrix(4);

    /** matrix to rotate about the z axis */
    private SquareMatrix rotateZ = new IdentityMatrix(4);

    /** concatenated rotation matrix */
    private SquareMatrix rotate = new IdentityMatrix(4);

    /** concatenated transformation matrix */
    private SquareMatrix transform = new IdentityMatrix(4);

    /** center of rotation */
    private Vertex center = new Vertex(0, 0, 0);

    /**
     * Initialize for a new set of transformations.
     */
    void init()
    {
        IdentityMatrix.convert(transform);
    }

    /**
     * Reset to the initial conditions.
     */
    void reset()
    {
        center = new Vertex(0, 0, 0);

        setTranslation(0, 0, 0);
        setScaling(1, 1, 1);
        setRotation(0, 0, 0);
    }

    /**
     * Set the translation matrix.
     * @param tx the change in the x direction
     * @param ty the change in the y direction
     * @param tz the change in the z direction
     */
    void setTranslation(float tx, float ty, float tz)
    {
        try {
            translate.set(3, 0, tx);
            translate.set(3, 1, ty);
            translate.set(3, 2, tz);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Set the scaling matrix.
     * @param sx the scaling factor in the x direction
     * @param sy the scaling factor in the y direction
     * @param sz the scaling factor in the z direction
     */
    void setScaling(float sx, float sy, float sz)
    {
        try {
            scale.set(0, 0, sx);
            scale.set(1, 1, sy);
            scale.set(2, 2, sz);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Set the rotation matrix.
     * @param thetaX amount (in radians) to rotate around the x axis
     * @param thetaY amount (in radians) to rotate around the y axis
     * @param thetaZ amount (in radians) to rotate around the z axis
     */
    void setRotation(float thetaX, float thetaY, float thetaZ)
    {
        try {
            float sin = (float) Math.sin(thetaX);
            float cos = (float) Math.cos(thetaX);

            // Rotate about the x axis.
            rotateX.set(1, 1,  cos);
            rotateX.set(1, 2, -sin);
            rotateX.set(2, 1,  sin);
            rotateX.set(2, 2,  cos);

            sin = (float) Math.sin(thetaY);
            cos = (float) Math.cos(thetaY);

            // Rotate about the y axis.
            rotateY.set(0, 0,  cos);
            rotateY.set(0, 2,  sin);
            rotateY.set(2, 0, -sin);
            rotateY.set(2, 2,  cos);

            sin = (float) Math.sin(thetaZ);
            cos = (float) Math.cos(thetaZ);

            // Rotate about the z axis.
            rotateZ.set(0, 0,  cos);
            rotateZ.set(0, 1, -sin);
            rotateZ.set(1, 0,  sin);
            rotateZ.set(1, 1,  cos);

            // Concatenate rotations.
            rotate = rotateX.multiply(rotateY.multiply(rotateZ));
        }
        catch(MatrixException ex) {}
    }

    /**
     * Transform a set of vertices based on previously-set
     * translation, scaling, and rotation.  Concatenate the
     * transformations in the order:  scale, rotate, translate.
     * @param vertices the vertices to transform
     */
    void transform(Vertex vertices[])
    {
        // Scale and rotate about the origin.
        toOrigin();
        scale();
        rotate();
        reposition();

        translate();

        // Apply the concatenated transformations.
        try {

            // Do the vertices.
            for (int i = 0; i < vertices.length; ++i) {
                Vertex v = vertices[i];
                v.multiply(transform);
            }

            // Do the center of rotation.
            center.multiply(transform);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Check for a bounce against any wall of the space.
     * Return true if bounced.
     * @param width the width of the space
     * @param height the height of the space
     * @param depth the depth of the space
     * @return true if bounced, else false
     */
    boolean bounced(float width, float height, float depth)
    {
        boolean b = false;

        try {

            // Bounced off the sides?
            if ((center.x() < 0) || (center.x() > width)) {
                translate.set(3, 0, -translate.at(3, 0));
                b = true;
            }

            // Bounced off the top or bottom?
            if ((center.y() < 0) || (center.y() > height)) {
                translate.set(3, 1, -translate.at(3, 1));
                b = true;
            }

            // Bounced off the front or back?
            if ((center.z() < 0) || (center.z() > depth)) {
                translate.set(3, 2, -translate.at(3, 2));

                // Invert the scale factor.
                float scaleFactor = 1/scale.at(0, 0);

                scale.set(0, 0, scaleFactor);
                scale.set(1, 1, scaleFactor);
                scale.set(2, 2, scaleFactor);

                b = true;
            }
        }
        catch(MatrixException ex) {}

        return b;
    }

    /**
     * Check if a line is behind the center of rotation.
     * @param v1 the vertex of one end of the line
     * @param v2 the vertex of the other end of the line
     * @return true if behind, else false
     */
    boolean behindCenter(Vertex v1, Vertex v2)
    {
        return (v1.z() < center.z()) && (v2.z() < center.z());
    }

    /**
     * Return a value from the concatenated transformation matrix.
     * @param r the value's row
     * @param c the value's column
     * @return the value
     */
    float at(int r, int c)
    {
        try {
            return transform.at(r, c);
        }
        catch(MatrixException ex) {
            return Float.NaN;
        }
    }

    /**
     * Concatenate a translation.
     * @param translate the translation matrix to use
     */
    private void translate(SquareMatrix translate)
    {
        try {
            transform = transform.multiply(translate);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Concatenate the preset translation.
     */
    private void translate()
    {
        translate(translate);
    }

    /**
     * Concatenate the preset scaling.
     */
    private void scale()
    {
        try {
            transform = transform.multiply(scale);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Concatenate the preset rotation.
     */
    private void rotate()
    {
        try {
            transform = transform.multiply(rotate);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Translate back to the origin.
     */
    private void toOrigin()
    {
        try {
            SquareMatrix tempTranslate = new IdentityMatrix(4);

            tempTranslate.set(3, 0, -center.x());
            tempTranslate.set(3, 1, -center.y());
            tempTranslate.set(3, 2, -center.z());

            translate(tempTranslate);
        }
        catch(MatrixException ex) {}
    }

    /**
     * Translate back into position.
     */
    private void reposition()
    {
        try {
            SquareMatrix tempTranslate = new IdentityMatrix(4);

            tempTranslate.set(3, 0, center.x());
            tempTranslate.set(3, 1, center.y());
            tempTranslate.set(3, 2, center.z());

            translate(tempTranslate);
        }
        catch(MatrixException ex) {}
    }
}