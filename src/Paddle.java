import java.awt.*;

public class Paddle extends Entity {
    public enum SIDE {L,R}
    private final int WIDTH = 10;
    private final int HEIGHT = 150;
    private final double moveMagnitude = 1.5;
    private SIDE side;


    public Paddle(SIDE s, Dimension dimension) {
        super(dimension);
        this.side = s;
        setX_pos((s == SIDE.L) ? 10 : dimension.width - WIDTH - 10);
        setY_pos(100);
    }

    public SIDE getSide() {
        return this.side;
    }
    public int getWidth() {return WIDTH;}
    public int getHeight() {return HEIGHT;}
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
        } else if(this.getMoveDirection() == DIRECTION.STAT) { //trend to 0
           this.setVelocity(0, (this.getYVelocity() > 1) ? 0.5 * this.getYVelocity() : 0);
        }
    }
    public void move() {
        if(!isWithinBounds()) {
            this.set_pos(this.getClosestBoundPoint());
            this.setVelocity(0, 0);
        }
        this.setY_pos(this.getY_pos() + this.getYVelocity());
    }

}
