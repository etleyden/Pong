import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ball extends Entity {

    private final int RADIUS = 30;
    private final Color COLOR = Color.WHITE;

    //we're gonna override the bounds functionality for paddles and
    //have solid bounds (where it reflects) and soft bounds (where it despawns and changes the points);
    private ArrayList<Point[]> solidBounds;
    private String message = "";

    public Ball() {
        super(new Dimension());
    }
    public Ball(int direction, Dimension bounds) {
        super(bounds.width / 2, bounds.height / 2, Color.WHITE, bounds);

        if(direction != -5 && direction != 5) throw new IllegalArgumentException("Direction must be -5 or 5");
        setVelocity(direction, 0);
        setHitBox(RADIUS, RADIUS);
    }

    public void move() {
        this.addPos(this.getVelocity());
    }
    public void update(Graphics2D g) {
        this.move();
        g.setColor(COLOR);
        g.fillOval((int) getX_pos(), (int) getY_pos(), RADIUS, RADIUS);
    }
    public int getHeight() { return RADIUS; }
    public void detectCollision(Entity e) {
        //check if the two boxes are overlapping
        int[] hitBoxE = e.getDynamicHitBox();
        int[] ourHitBox = this.getDynamicHitBox();
        //calculate the area of the intersection between the boxes
        //src: https://www.educative.io/answers/how-to-check-if-two-rectangles-overlap-each-other
        boolean horizontalOverlap = Math.min(hitBoxE[2], ourHitBox[2]) > Math.max(hitBoxE[0], ourHitBox[0]);
        boolean verticalOverlap = Math.min(hitBoxE[3], ourHitBox[3]) > Math.max(hitBoxE[1], ourHitBox[1]);
        //calculate which direction to flip.
        if(horizontalOverlap && verticalOverlap) { //! Collision !
            if(e instanceof Paddle) {
                //find the distance from the center of the paddle.
                int centerOfPaddle = hitBoxE[3] - (e.getHeight() / 2);
                int centerOfBall = ourHitBox[3] - (this.getHeight() / 2);
                this.setVelocity(-getXVelocity(), 0.1 * (centerOfBall - centerOfPaddle));
            } else {
                this.setVelocity(getXVelocity(), -getYVelocity());
            }
        }
    }
    public boolean detectScreenExit(Paddle l, Paddle r) {
        if(getX_pos() < 0) {
            r.addPoint();
            return true;
        } else if(getX_pos() > bounds[1].x) {
            l.addPoint();
            return true;
        }
        return false;
    }
    public void setMessage(String str) {
        this.message = str;
    }
}
