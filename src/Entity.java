import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Entity {
    private Point[] bounds;
    private double x_pos;
    private double y_pos;
    private Color color;
    private Point2D.Double velocity;
    private Point[] hitBox;
    private DIRECTION moveDirection;
    public enum DIRECTION {
        UP, DOWN, LEFT, RIGHT, STAT
    }

    public Entity(Dimension bounds) {
        this(50, 50, Color.WHITE, bounds);
    }
    public Entity(int x, int y) {
        this(x, y, Color.WHITE, new Dimension(100, 100));
    }
    public Entity(int x, int y, Color c, Dimension bounds) {
        x_pos = x;
        y_pos = y;
        velocity = new Point2D.Double(0, 0);
        this.color = c;
        this.bounds = new Point[]{new Point(0, 0), new Point(bounds.width, bounds.height)};
    }

    public boolean isWithinBounds() {
        return isWithinXBounds() && isWithinYBounds();
    }
    public boolean isWithinXBounds() {
        return ((double)this.bounds[0].x <= x_pos && x_pos <= (double)bounds[1].x);
    }
    public boolean isWithinYBounds() {
        return ((double)bounds[0].y <= y_pos && y_pos<= (double)bounds[1].y);
    }
    public Point getClosestBoundPoint() {
        if(isWithinYBounds()) return new Point((x_pos <= bounds[0].x) ? bounds[0].x : bounds[1].x, (int) y_pos);
        if(isWithinXBounds()) return new Point((int)x_pos, (y_pos <= bounds[0].y) ? bounds[0].y : bounds[1].y);

        //if out in the corner then we have a real problem -- functionality needed later on.
        return new Point();
    }
    public Point2D.Double getPos() {return new Point2D.Double(x_pos, y_pos);}
    public double getX_pos() {return x_pos;}
    public double getY_pos() {return y_pos;}
    public void setX_pos(double x) {x_pos = x;}
    public void setY_pos(double y) {
        y_pos = y;
    }
    public void set_pos(Point point) {
        x_pos = point.x;
        y_pos = point.y;
    }

    public Color getColor() {
        return color;
    }
    public void update(Graphics2D g2d) {

    }
    public void addVelocity(double x, double y) {
        velocity.x += x;
        velocity.y += y;
    }
    public double getYVelocity() {
        return velocity.y;
    }
    public double getXVelocity() {
        return velocity.x;
    }
    public Point2D.Double getVelocity() {
        return velocity;
    }
    public DIRECTION getMoveDirection() {
        return moveDirection;
    }
    public void updateDirection(DIRECTION d) {
        moveDirection = d;
    }
    public void setVelocity(double x, double y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }
}
