package numbercruncher.program9_1;

import java.awt.*;

/**
 * The panel that represents the enclosed 3-D space
 * for the tumbling wire-frame cube.
 */
public class CubePanel extends Panel
{
    private static final float MAX_TRANSLATE = 5;
    private static final float MAX_SCALING   = 3;

    /** width of space */   private int width;
    /** height of space */  private int height;
    /** depth of space */   private int depth;

    /** image buffer */             private Image    buffer;
    /** buffer graphics context */  private Graphics bg;

    /** true for first draw */  private boolean first = true;

    /** wire frame cube */  private WireFrameCube  cube;
    /** transformation */   private Transformation transformation;
    /** parent panel */     private TransformationPanel parent;

    /**
     * Constructor.
     * @param transformation the graphics transformation
     * @param parent the parent panel
     */
    CubePanel(Transformation transformation,
              TransformationPanel parent)
    {
        this.transformation = transformation;
        this.parent         = parent;
        this.cube           = new WireFrameCube();

        setBackground(Color.white);
    }

    /**
     * Reset the cube to its starting position.
     */
    public void reset()
    {
        cube  = new WireFrameCube();
        first = true;
        bg    = null;

        repaint();
    }

    /**
     * Draw the contents of the panel.
     */
    public void draw()
    {
        if (bg == null) return;
        bg.clearRect(0, 0, width, height);

        transformation.init();

        if (first) {
            firstDraw();
        }
        else {
            subsequentDraw();
        }

        repaint();
        parent.updateMatrixDisplay();
    }

    /**
     * Paint without first clearing.
     * @param g the graphics context
     */
    public void update(Graphics g) { paint(g); }

    /**
     * Paint the contents of the image buffer.
     * @param g the graphics context
     */
    public void paint(Graphics g)
    {
        // Has the buffer been created?
        if (bg == null) {
            Rectangle r = getBounds();

            width  = r.width;
            height = r.height;
            depth  = width;

            // Create the image buffer and get its graphics context.
            buffer = createImage(width, height);
            bg     = buffer.getGraphics();

            draw();
        }

        // Paint the buffer contents.
        g.drawImage(buffer, 0, 0, null);
    }

    /**
     * First time drawing.
     */
    private void firstDraw()
    {
        // Scale and move to the center.
        transformation.setScaling(50, 50, 50);
        transformation.setTranslation(width/2, height/2, depth/2);

        cube.draw(bg, transformation);

        // Random subsequent translations.
        float xDelta = (float) (2*MAX_TRANSLATE*Math.random()
                                    - MAX_TRANSLATE);
        float yDelta = (float) (2*MAX_TRANSLATE*Math.random()
                                    - MAX_TRANSLATE);
        float zDelta = (float) (2*MAX_TRANSLATE*Math.random()
                                    - MAX_TRANSLATE);

        transformation.setTranslation(xDelta, yDelta, zDelta);

        // Set the scale factor based on the space's depth and
        // whether the cube is moving towards or away from the viewer.
        // At maximum z, the cube should be twice its original size,
        // and at minimum z, it should be half its original size.
        float steps       = (depth/2)/Math.abs(zDelta);
        float scaleFactor = (float) Math.pow(MAX_SCALING, 1/steps);
        if (zDelta < 0) scaleFactor = 1/scaleFactor;

        transformation.setScaling(
                            scaleFactor, scaleFactor, scaleFactor);

        setRandomRotation();
        first = false;
    }

    /**
     * Subsequent drawing.
     */
    private void subsequentDraw()
    {
        // Draw the transformed cube.
        cube.draw(bg, transformation);

        // If there was a bounce, set new random rotation angles.
        if (transformation.bounced(width, height, depth)) {
            setRandomRotation();
        }
    }

    /**
     * Set random rotation angles about the axes.
     */
    private void setRandomRotation()
    {
        transformation.setRotation(
                        (float) (0.1*Math.random()),     // x axis
                        (float) (0.1*Math.random()),     // y axis
                        (float) (0.1*Math.random()));    // z axis
    }
}