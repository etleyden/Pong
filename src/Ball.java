import java.awt.*;

public class Ball extends Entity {

    private final int RADIUS = 30;
    private final Color COLOR = Color.WHITE;



    public Ball() {
        super(new Dimension());
    }
    public Ball(int direction, Dimension bounds) {
        super(bounds.width / 2, bounds.height / 2, Color.WHITE, bounds);

        System.out.println("Ball time");

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
}
