import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerInput implements KeyListener {

    Paddle left_paddle, right_paddle;

    public PlayerInput(Paddle left_paddle, Paddle right_paddle) {
        this.left_paddle = left_paddle;
        this.right_paddle = right_paddle;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_S:
                left_paddle.down(true);
                break;
            case KeyEvent.VK_W:
                left_paddle.up(true);
                break;
            case KeyEvent.VK_UP:
                right_paddle.up(true);
                break;
            case KeyEvent.VK_DOWN:
                right_paddle.down(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_S:
                left_paddle.down(false);
                break;
            case KeyEvent.VK_W:
                left_paddle.up(false);
                break;
            case KeyEvent.VK_UP:
                right_paddle.up(false);
                break;
            case KeyEvent.VK_DOWN:
                right_paddle.down(false);
                break;
        }
    }
}
