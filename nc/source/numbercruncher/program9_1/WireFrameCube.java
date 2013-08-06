package numbercruncher.program9_1;

import java.awt.*;

/**
 * A wire-frame cube to transform and display.
 */
class WireFrameCube
{
    /**
     * Represent each face of the cube.
     */
    private class Face
    {
        /** indices of the face's vertices */   int indices[];

        /**
         * Constructor.
         * @param v1 the first vertex
         * @param v2 the second vertex
         * @param v3 the third vertex
         * @param v4 the fourth vertex
         */
        Face(int v1, int v2, int v3, int v4)
        {
            indices = new int[] {v1, v2, v3, v4};
        }
    }

    /** The cube's vertices. */
    private Vertex vertices[] = {
        new Vertex(-0.5f, -0.5f, -0.5f),
        new Vertex(+0.5f, -0.5f, -0.5f),
        new Vertex(-0.5f, +0.5f, -0.5f),
        new Vertex(+0.5f, +0.5f, -0.5f),
        new Vertex(-0.5f, -0.5f, +0.5f),
        new Vertex(+0.5f, -0.5f, +0.5f),
        new Vertex(-0.5f, +0.5f, +0.5f),
        new Vertex(+0.5f, +0.5f, +0.5f),
    };

    /** The cube's faces. */
    private Face faces[] = {
        new Face(0, 1, 3, 2),
        new Face(0, 1, 5, 4),
        new Face(2, 3, 7, 6),
        new Face(0, 4, 6, 2),
        new Face(1, 5, 7, 3),
        new Face(4, 5, 7, 6),
    };

    /**
     * Draw the transformed cube.
     * @param g the graphics context
     * @param transformation the transformation to apply
     */
    void draw(Graphics g, Transformation transformation)
    {
        // Transform the vertices.
        transformation.transform(vertices);

        // Loop for each face.
        for (int i = 0; i < faces.length; ++i) {
            int indices[] = faces[i].indices;

            // Draw the edges of the face.
            for (int j = 0; j < indices.length; ++j) {
                int k  = (j + 1)%indices.length;
                int c1 = Math.round(vertices[indices[j]].x());
                int r1 = Math.round(vertices[indices[j]].y());
                int c2 = Math.round(vertices[indices[k]].x());
                int r2 = Math.round(vertices[indices[k]].y());

                // Set the color based on the edge's position.
                Color color =
                    transformation.behindCenter(vertices[indices[j]],
                                                vertices[indices[k]])
                        ? Color.lightGray : Color.black;

                // Draw the edge.
                g.setColor(color);
                g.drawLine(c1, r1, c2, r2);
            }
        }
    }
}