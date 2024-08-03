package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeHeight(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeWidth(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeHeight(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeWidth(false);
                break;
            // case KeyEvent.VK_UP:
            //     gamePanel.changeYDelta(false);
            //     break;
            // case KeyEvent.VK_DOWN:
            //     gamePanel.changeYDelta(true);
            //     break;
            case KeyEvent.VK_LEFT:
                gamePanel.changeXDelta(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.changeXDelta(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}