import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Pong extends JPanel {

    private static Paddle left_paddle;
    private static Paddle right_paddle;

    private final Color backgroundColor = Color.BLACK;
    private static Dimension screenSize;

    static ArrayList<Entity> entities;

    public Pong() {
        entities = new ArrayList<>();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //create entities
        left_paddle = new Paddle(Paddle.SIDE.L, screenSize);
        entities.add(left_paddle);
        right_paddle = new Paddle(Paddle.SIDE.R, screenSize);
        entities.add(right_paddle);
        Ball b = new Ball();
        entities.add(b);


    }

    public void init() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                constructGame();
            }
        });

    }
    public void constructGame() {
        JFrame f = new JFrame();
        this.setBackground(Color.BLACK);
        f.add(this);
        f.setSize(screenSize);
        //this will get the ball rolling. It will eventually call our overridden paint method which is kind of like an entry point
        f.setVisible(true);

        //add listeners
        PlayerInput handler = new PlayerInput(left_paddle, right_paddle);
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
        for(Entity e : entities) {
            e.update(g2d);
        }
    }

}
