import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputReader extends KeyAdapter{

    private final Handler handler;
    private static int lastKeyPressed;
    private static int lastKeyReleased;

    public InputReader(Handler handler){
        this.handler = handler;
    }

    public static int getLastKeyPressed() { return lastKeyPressed; }
    public static int getLastKeyReleased() { return lastKeyReleased; }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        lastKeyReleased = -1;
        lastKeyPressed = key;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        lastKeyPressed = -1;
        lastKeyReleased = key;

    }
}
