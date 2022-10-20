import java.awt.*;

public class Paddle extends Entity {
    public enum SIDE {L,R}
    private final int WIDTH = 10;
    private final int HEIGHT = 150;
    private final double moveMagnitude = 1.5;



    public Paddle(SIDE s, Dimension dimension) {
        super(dimension);
        System.out.print("Constructor called");
        setX_pos((s == SIDE.L) ? 0 : dimension.width - WIDTH);
        setY_pos(100);
    }

    @Override
    public void update(Graphics2D g) {
        this.move();
        this.updateVelocity();

        g.setColor(getColor());
        g.fillRect((int)getX_pos(), (int)getY_pos(), WIDTH, HEIGHT);
    }
    public void up(boolean active) {
        this.updateDirection((active) ? DIRECTION.UP : DIRECTION.STAT);
    }
    public void down(boolean active) {
        this.updateDirection((active) ? DIRECTION.DOWN : DIRECTION.STAT);
    }
    public void updateVelocity() {
        if(this.getMoveDirection() == DIRECTION.UP && Math.abs(this.getYVelocity()) < 10) {
            this.addVelocity(0, -moveMagnitude);
        } else if(this.getMoveDirection() == DIRECTION.DOWN && Math.abs(this.getYVelocity()) < 10) {
            this.addVelocity(0, moveMagnitude);
        } else if(this.getMoveDirection() == DIRECTION.STAT || !this.isWithinBounds()) { //trend to 0
           this.setVelocity(0, 0);
        }
    }
    public void move() {

        this.setY_pos(this.getY_pos() + this.getYVelocity());
    }

}
