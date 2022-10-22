import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pong extends JPanel {
    /**
     * The paddle that gets initialized on the left
     */
    private static Paddle left_paddle;
    /**
     * The paddle that gets initialized on the right
     */
    private static Paddle right_paddle;
    private Ball ball;
    /**
     * The color of the rectangle that gets drawn over the canvas each refresh
     */
    private final Color backgroundColor = Color.BLACK;
    /**
     * Initial Screen dimensions
     */
    private static Dimension screenSize;

    /**
     * An ArrayList of entities so we can just call entities.update() in a loop
     */

    boolean ballSpawned = false;

    public Pong() {
        //initialize screen size
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //create entities -- left paddle
        left_paddle = new Paddle(Paddle.SIDE.L, screenSize);

        //right paddle
        right_paddle = new Paddle(Paddle.SIDE.R, screenSize);




    }
    public Paddle[] getPaddles() {
        return new Paddle[]{left_paddle, right_paddle};
    }

    public void init() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                constructGame(); //<-- this is a wrapper method that allows us to refer to Pong fields non-statically
            }
        });

    }
    public void constructGame() {
        //the frame holds the panel
        JFrame f = new JFrame();
        this.setBackground(backgroundColor); //not sure what this does tbh
        f.add(this);
        f.setSize(screenSize);
        //this will get the ball rolling. It will eventually call our overridden paint method which is kind of like an entry point
        f.setVisible(true);

        //add listeners
        PlayerInput handler = new PlayerInput(this);
        f.addKeyListener(handler);

        //set the refresh
        Timer timer = new Timer(10, e -> {
            this.repaint();
        });
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        startLoop(g2d);
    }
    public void startLoop(Graphics2D g2d) {
        left_paddle.update(g2d);

        right_paddle.update(g2d);

        if(ballSpawned) {
            ball.update(g2d);

            ball.detectCollision(left_paddle);
            ball.detectCollision(right_paddle);

            //detect collision with top
            ball.detectCollision(new Entity(0, 0, new Dimension(screenSize.width, 1)));

            //detect collision with bottom
            ball.detectCollision(new Entity(0, screenSize.height, new Dimension(screenSize.width, 1)));
        }

    }

    public boolean isBallSpawned() {return ballSpawned;}
    public void spawnBall() {
        //flip a coin
        ballSpawned = true;
        int x_direction = (Math.floor(Math.random() * 2) == 0) ? -5 : 5;
        ball = new Ball(x_direction, screenSize);
    }
}
