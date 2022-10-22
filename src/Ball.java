import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ball extends Entity {

    private final int RADIUS = 30;
    private final Color COLOR = Color.WHITE;

    //we're gonna override the bounds functionality for paddles and
    //have solid bounds (where it reflects) and soft bounds (where it despawns and changes the points);
    private ArrayList<Point[]> solidBounds;

    public Ball() {
        super(new Dimension());
    }
    public Ball(int direction, Dimension bounds) {
        super(bounds.width / 2, bounds.height / 2, Color.WHITE, bounds);

        if(direction != -5 && direction != 5) throw new IllegalArgumentException("Direction must be -5 or 5");
        setVelocity(direction, 0);
    }

    public void move() {
        this.addPos(this.getVelocity());
    }
    public void update(Graphics2D g) {
        this.move();

        g.setColor(COLOR);
        g.fillOval((int) getX_pos(), (int) getY_pos(), RADIUS, RADIUS);
    }
    public void detectCollision(Paddle p) {
        if(p.getSide() == Paddle.SIDE.L) {
            detectCollision(new Point[] {
                    new Point((int)p.getX_pos() + p.getWidth(), (int) p.getY_pos()),
                    new Point((int)p.getX_pos() + p.getWidth(), (int) (p.getY_pos() + p.getHeight()))
            });
        } else {
            detectCollision(new Point[] {
                    new Point((int) p.getX_pos(),(int) p.getY_pos()),
                    new Point((int) p.getX_pos(), (int) (p.getY_pos() + p.getHeight()))
            });
        }
    }
    public void detectCollision(Point[] line) {
        //check if the ball is touching the line, if so, check the direction that the line is in, and reflect based on that.
        //x and y represent the upper left corner, but visual contact points will be halfway to length/width
        //velocity will determine which sides we check for contact.

        //:1. visual contact points to compare to our line horizontal/vertical
        Point hContact = new Point();
        Point vContact = new Point();
        vContact.x = (int) getX_pos() + ((getXVelocity() < 0) ? 0 : RADIUS); //if x velocity is negative, then vertical contact will be on the left. Else, right.
        vContact.y = (int) (getY_pos() + RADIUS / 2); //the middle of the circle

        hContact.x = (int) getX_pos() + RADIUS / 2;
        hContact.y = (int) getY_pos() + (((getYVelocity() < 0)) ? 0 : RADIUS);

        //Note: Current state --> We need to move ball away from the collision bound in this method so it doesn't get stuck
        if((line[0].x == line[1].x) && //if the x is the same (isVertical) AND the ball is past the line (depends on velocity direction)
                ((getXVelocity() < 0 && vContact.x < line[0].x) || (getXVelocity() > 0 && vContact.x > line[0].x))) {
                //Collision! Reflect x direction
            this.addVelocity(-2 * this.getXVelocity(), 0);
        } else if((line[0].y == line[1].y) &&
                ((getYVelocity() < 0 && hContact.y < line[0].y) || (getYVelocity() > 0 && hContact.y > line[0].y))) {
                //Collision! Reflect y direction
            this.addVelocity(0, -2 * this.getYVelocity());
        }
    }
}
